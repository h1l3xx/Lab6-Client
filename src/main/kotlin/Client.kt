import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.util.*

class Client {
    fun main() {
        val sender = Sender("localhost", 1050)
        val message = "HelloWorldAll"


        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                sender.sendMessage(message)
            }
        }, 1000, 1000)
    }
}
