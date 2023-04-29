package handkers
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
class Serialization {
        fun serialize(data: HashMap<String,String>): String? {
            val mapper = ObjectMapper().registerModule(JavaTimeModule())
            return mapper.writeValueAsString(data)
        }
}