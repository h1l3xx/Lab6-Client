package command

import handkers.desir



class CommandList {
    private var commandList  : HashMap<String, String> = HashMap()
    fun createComList(){
        for(i in 0.. desir().size-1){
            commandList.put(i.toString(), desir()[i]["name"].toString())
        }
        println(commandList.values)
    }


}