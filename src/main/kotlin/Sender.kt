import java.net.DatagramSocket
import java.net.DatagramPacket
import java.net.InetAddress
import java.io.IOException

class Sender(host: String, port: Int) {
    private var host : String? = host
    private var port : Int? = port

    fun sendMessage(message : String){
        try {
            val data : ByteArray = message.toByteArray()
            val address : InetAddress = InetAddress.getByName(host)
            val pack : DatagramPacket = DatagramPacket(data, data.size, address, port!!)
            val socket = DatagramSocket()
            socket.send(pack)
            socket.close()
        }catch (e : IOException){
            e.printStackTrace()
        }
    }
}