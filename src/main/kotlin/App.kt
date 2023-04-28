
import command.CommandList
import command.commandList
import handkers.*
import printers.UPrinter
import java.util.Scanner

val scanner = Scanner(System.`in`)
val client = Client()

fun main(){


        client.sendMessage("")
        var data = Deserializator().deserialize(client.getFirstMessage())
        CommandList().createCL(data)
        println( commandList)
//        println(data)
//        println(client.getFirstMessage())
//        client.sendMessage(scanner.nextLine())
//        println(client.getMessage())

//        println( client.getMessage())



}