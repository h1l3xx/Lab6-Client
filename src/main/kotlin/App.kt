
import command.CommandList
import command.commandList
import handkers.*
import printers.UPrinter
import java.util.Scanner

val scanner = Scanner(System.`in`)
val client = Client()

fun main(){
        client.sendMessage(HashMap())
        var data = Deserializator().deserialize(client.getMessage())
        CommandList().createCL(data)
        CommandList().showCommand()
        val smtMap : HashMap<String, String> = data[data.lastIndex]
        println( CommandList().createFL(smtMap))
        val tesr:HashMap<String,String> = HashMap()
        while (true) {
                tesr["name"] = scanner.nextLine()
                client.sendMessage(tesr)
                client.check()
                println( client.getMessage())
        }

//        println(data)
//        println(client.getFirstMessage())
//        client.sendMessage(scanner.nextLine())
//        println(client.getMessage())

//        println( client.getMessage())



}