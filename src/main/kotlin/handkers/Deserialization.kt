package handkers
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.databind.ObjectMapper
class Deserialization {
        fun deserialize(data: String): List<HashMap<String, String>> {
            val objectMapper = ObjectMapper()
            return objectMapper.readValue<List<HashMap<String,String>>>(data)
        }
}
