//package handkers//import java.io.IOException
//import java.net.DatagramPacket
//import java.net.DatagramSocket
//import java.net.InetAddress
//import java.util.*
//
//class Client {
//    fun main() {
//        val sender = Sender(172.28.28.216:1050 )//ip in localnetwork
//        val message = "HelloWorldAll"
//
//
//        val timer = Timer()
//        timer.scheduleAtFixedRate(object : TimerTask() {
//            override fun run() {
//                sender.sendMessage(message)
//            }
//        }, 1000, 1000)
//    }
//}
