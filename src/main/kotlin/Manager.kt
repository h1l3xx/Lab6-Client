import command.*
import handkers.Deserialization
import handkers.MapBuilder

class Manager {
    fun process(){
        val validator = Validator()
        val commands = CommandList()
        val data = Deserialization().deserialize(client.getMessage())
        commands.setCommandList(data)
        commands.showCommands()

        while (true){
            val command = scanner.nextLine()
            val commandAndArguments = command.split(" ")
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
        }else{
            validateArguments(command, arguments)
        }
    }
    private fun validateArguments(c: String, a : List<String>){
        val description = commandList[c]!![Var.description]!!
        if (description != "" && !description.contains("field") && !description.contains(Var.wayToFile)){
            val returnValue = Validator().validateOneArgument(a[0], description).toString()
            if (returnValue == Messages.errorValue || returnValue == Messages.errorType){
                uPrinter.print { returnValue }
            }else{
                client.sendMessage(MapBuilder().buildMap("$c $returnValue"))
            }
        }else if (!description.contains(Var.allFields)  && !description.contains(Var.wayToFile)){
            val returnValue = Validator().validateMoreArg(a).toString()
            if (returnValue == Messages.errorValue || returnValue == Messages.errorType || returnValue == Messages.errorField){
                uPrinter.print { returnValue }
            }else{
                client.sendMessage(MapBuilder().buildMap(returnValue))
            }
        }else if (description.contains(Var.allFields)  && !description.contains(Var.wayToFile)){
            val answer = Validator().allFields(a)
            if (answer == Messages.errorType || answer == Messages.errorField || answer == Messages.errorValue){
                uPrinter.print { answer }
            }else{
                client.sendMessage(MapBuilder().buildMap("$c $answer"))
            }
        }else{
            val answer = Validator().workWithFile(a).toString()
            println(answer)
        }
    }
}