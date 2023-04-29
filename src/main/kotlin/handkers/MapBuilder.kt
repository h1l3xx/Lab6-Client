package handkers

import command.Var

class MapBuilder {
    fun buildMap(line : String) : HashMap<String, String> {
        val returnMap = HashMap<String, String>()
        returnMap[Var.name] = line
        return returnMap
    }
}