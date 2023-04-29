import handkers.Config
import handkers.Serialization
import java.net.InetSocketAddress
import java.net.SocketAddress
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel

class Client {
    private val serverAddress: SocketAddress = InetSocketAddress(Config.servAdr,Config.port)
    private val channel: DatagramChannel = DatagramChannel.open()

    init {
        channel.bind(null)
        channel.connect(serverAddress)
        channel.configureBlocking(false)
    }

    fun getMessage(): String {
        var data: String? = null
        while (data.isNullOrEmpty()) {
            val buffer: ByteBuffer = ByteBuffer.allocate(65535)
            channel.receive(buffer)
            buffer.flip()
            val bytes = ByteArray(buffer.remaining())
            buffer.get(bytes)
            data = (String(bytes))
        }
        return data
    }

//    fun getFirstMessage(): String {
//        var data: String? = null
//        while (data.isNullOrEmpty()) {
//            val buffer: ByteBuffer = ByteBuffer.allocate(65535)
//            channel.receive(buffer)
//            buffer.flip()
//            val bytes = ByteArray(buffer.remaining())
//            buffer.get(bytes)
//            data = String(bytes)
//
//        }
//        return data
//    }
    fun sendMessage(mess: HashMap<String,String>) {
        channel.send(ByteBuffer.wrap(Serialization().serialize(mess)!!.toByteArray()), serverAddress)
    }

    //fun check() {
    //    print( channel.isConnected);
    //}
}
