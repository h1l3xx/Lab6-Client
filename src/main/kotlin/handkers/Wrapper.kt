//package handkers
//
//
//
//import client
//import kotlinx.serialization.Serializable
//import kotlinx.serialization.decodeFromString
//import kotlinx.serialization.json.Json
//
//
//@Serializable
//data class Wrapper(val map : List<HashMap<String, String>>) {}
//fun desir() : List<HashMap<String, String>>{
//    return Json.decodeFromString<Wrapper>(client.getFirstMessage()).map
//}
//
//@Serializable
//data class SendMes(val name : String){}
//fun deser(string: String) : String{
//    return Json.decodeFromString<SendMes>(string).name
//}
