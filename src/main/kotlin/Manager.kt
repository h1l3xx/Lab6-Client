import command.*
import handkers.Connect
import handkers.Deserialization
import handkers.MapBuilder
import printers.UPrinter
import java.util.Scanner
import kotlin.system.exitProcess


class Manager {

    private val client = Client()
    val uPrinter = UPrinter()
    private val scanner = handkers.Scanner()
    fun process(){
        val validator = Validator()
        val commands = CommandList()
        var running = true

        client.sendMessage(HashMap())

        val data = Deserialization().deserialize(client.getMessage())
        running = if (data.isNotEmpty()){
            commands.setCommandList(data)
            commands.showCommands()
            true

        }else{
            false
        }


        while (running){

            val command = scanner.readLine()

            val commandAndArguments = command!!.split(" ")
            val name = commandAndArguments[0]
            var arguments = commandAndArguments.drop(1)


            if(validator.checkCommand(name)){
                if (arguments.isNotEmpty() && arguments.last() == ""){
                    arguments = arguments.dropLast(1)
                }
                validator.manege(name, arguments)
            }
            else{
                uPrinter.print{ "Такой команды не существует. Узнать подробнее: help" }
            }
        }
    }

    fun continueManage(command : String, arguments : List<String>){
        if (arguments.isEmpty() && !commandList[command]!![Var.description]!!.contains(Var.allFields)){
            val mapForSand = MapBuilder().buildMap(command)
            client.sendMessage(mapForSand)
            uPrinter.print { getMessage()}
        }else{
            if (validateArguments(command, arguments)){
                uPrinter.print { getMessage()}
            }
        }
    }
    private fun validateArguments(c: String, a : List<String> ): Boolean{
        val description = commandList[c]!![Var.description]!!
        if (description != "" && !description.contains("field") && !description.contains(Var.wayToFile)){
            val returnValue = Validator().validateOneArgument(a[0], description).toString()
            return if (badValue(returnValue)){
                uPrinter.print { returnValue }
                false
            }else{
                client.sendMessage(MapBuilder().buildMap("$c $returnValue"))
                true
            }
        }else if (!description.contains(Var.allFields)  && !description.contains(Var.wayToFile)){
            val returnValue = Validator().validateMoreArg(a).toString()
            return if (badValue(returnValue)){
                uPrinter.print { returnValue }
                false
            }else{
                client.sendMessage(MapBuilder().buildMap("$c $returnValue"))
                true
            }
        }else if (description.contains(Var.allFields)  && !description.contains(Var.wayToFile)){
            val answer = Validator().allFields(a)
            return if (badValue(answer)){
                uPrinter.print { answer }
                false
            }else{
                client.sendMessage(MapBuilder().buildMap("$c $answer"))
                true
            }
        }else{
            if (Validator().workWithFile(a).isNotEmpty()){
                client.sendMessage(MapBuilder().buildMapFromList(Validator().workWithFile(a)))
            }
            return true
        }
    }
    private fun getMessage(): String {
        return try{
            val value = Deserialization().deserializeAnswer(client.getMessage()).values.toString().dropLast(1).drop(1)
            if(value == Var.exit){
                exitProcess(1)
            }
            return value
        }catch (e : Exception){
            Connect().tryAgain()
            return "Отсутствует подключение к серверу. Повторная попытка через 10 секунд."
        }
    }
    private fun badValue(returnValue : String): Boolean{
       return returnValue == Messages.errorType || returnValue == Messages.errorField || returnValue == Messages.errorValue
    }
}