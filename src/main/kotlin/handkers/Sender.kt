package handkers

import java.io.IOException
import java.net.InetSocketAddress
import java.net.SocketAddress
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel

class SenderProgram {
    @Throws(IOException::class)
    fun startSender(): DatagramChannel {
        val sender = DatagramChannel.open()
        // SocketAddress
        val address: SocketAddress? = null
        sender.bind(address)
        sender.configureBlocking(false)
        return sender
    }

    @Throws(IOException::class)
    fun sendMessage(
        sender: DatagramChannel, msg: String,  //
        receiverAddress: SocketAddress?
    ) {
        val buffer = ByteBuffer.wrap(msg.toByteArray())
        sender.send(buffer, receiverAddress)
    }

    fun mailing(mess: String) {
        val sender = startSender()
        val receiverAddress = InetSocketAddress("192.168.1.107", 3000)
        sendMessage(sender, mess, receiverAddress)
    }
}