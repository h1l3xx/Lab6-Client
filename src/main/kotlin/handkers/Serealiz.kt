package handkers
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
class Serealiz {
        fun serialize(data: String): String? {
            val mapper = ObjectMapper().registerModule(JavaTimeModule())
            return mapper.writeValueAsString(data)
        }
}