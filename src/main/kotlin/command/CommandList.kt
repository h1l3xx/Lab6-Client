package command

import printers.UPrinter
object Var{
    const val id = "id"
    const val name = "name"
    const val coordinateX = "coordX"
    const val coordinateY = "coordY"
    const val area = "area"
    const val population = "population"
    const val meters = "metersAboveOfSeaLevel"
    const val agl = "agglomeration"
    const val climate = "climate"
    const val government = "government"
    const val birthday = "birthday"
    const val age = "age"
    const val index = "index"
    const val allFields = "all fields"
    const val numberOfFields = "number of fields"
    const val wayToFile = "Way to File"
    const val True = "True"
    const val False = "False"
    const val numbersOfId = "numbers of id"
    const val description = "description"
    const val str = "String"
    const val long = "long"
    const val integer = "int"
    const val double = "double"
    const val float = "float"
}
var commandList: HashMap<String,HashMap<String,String>> = HashMap()
var fieldMap: HashMap<String,String> = HashMap()
class CommandList {
    fun createMapThird(data: List<HashMap<String,String>>):HashMap<String,String>{
        val secData: HashMap<String,String> = HashMap()
        for (i in 0..data.size-2){
            secData["min"] = data[i]["min"].toString()
            secData["max"] = data[i]["max"].toString()
            secData["between"] = data[i]["between"].toString()
        }
        return secData
    }

    fun createCL(data: List<HashMap<String,String>>){
        for (i in 0..data.size-2){
           commandList[data[i]["name"].toString()] = createMapThird(data)
        }
    }
    fun showCommand(){
            UPrinter().print{ commandList.keys.toString()}
    }

    fun createFL(data: HashMap<String,String>): HashMap<String, String> {
        fieldMap = data
        return fieldMap
    }


}