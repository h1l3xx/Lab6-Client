import handkers.Config
import handkers.Serialization
import java.net.InetSocketAddress
import java.net.PortUnreachableException
import java.net.SocketAddress
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit


class Client {
    private val serverAddress: SocketAddress = InetSocketAddress(Config.servAdr,Config.port)
    private val channel: DatagramChannel = DatagramChannel.open()

    init {
        channel.bind(null)
        channel.connect(serverAddress)
        channel.configureBlocking(false)
    }

    fun getMessage(): String {
        try {
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
        }catch (e : PortUnreachableException){
            return "Отсутствует подключение к серверу, повторная попытка произойдёт через 10 секунд."
        }
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
