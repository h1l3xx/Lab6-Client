import command.CommandList
import handkers.*
import printers.UPrinter

val iteration = Iteration()
val tttt = CommandList()
fun main(){


        iteration.oneIteration("pizda")
//        tttt.loop()
        println(desir())
        for(i in 0.. desir().size-1){
                println(desir()[i]["name"])
        }
        iteration.othersIteration("zalupa")


}