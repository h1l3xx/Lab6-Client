package command

var commandList: HashMap<String,HashMap<String,String>> = HashMap()
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

}