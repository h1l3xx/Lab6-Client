package command

import java.io.BufferedReader
import java.io.FileReader

class FileManager {
    fun file(wayToFile : String) : String{
        var file = """"""
        var lines = 0
        return try{
            val bufferReader = BufferedReader(FileReader(wayToFile))
            while (bufferReader.readLine() != null){
                lines += 1
            }
            val buf = BufferedReader(FileReader(wayToFile))
            while (lines > 0){
                file += buf.readLine() + "\n"
                lines -= 1
            }
            val commands = file.lines().toString()
            commands
        }catch (e : Exception){
            "Error"
        }
    }
}