package handkers

import iteration
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


@Serializable
data class Wrapper(val map : List<HashMap<String, String>>) {}
fun desir() : List<HashMap<String, String>>{
    return Json.decodeFromString<Wrapper>(iteration.getFirstData()).map
}