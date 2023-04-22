package handkers

import java.io.IOException
import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel


    class ReceiverProgram {
        @Throws(IOException::class)
        fun startReceiver(): DatagramChannel {
            val receiver = DatagramChannel.open()
            val address = InetSocketAddress("172.28.110.33", 1050)//localnetwork ip
            receiver.bind(address)
//        println("Receiver started at #$address")
            return receiver
        }

        @Throws(IOException::class)
        fun receiveMessage(receiver: DatagramChannel): String {
            val buffer = ByteBuffer.allocate(65535)
            val senderAddress = receiver.receive(buffer)
            val message = extractMessage(buffer)
//        println("Received message from sender: $senderAddress")
            return message
        }

        private fun extractMessage(buffer: ByteBuffer): String {
            buffer.flip()
            val bytes = ByteArray(buffer.remaining())
            buffer[bytes]
            return String(bytes)
        }

        @Throws(IOException::class)
        fun main(receiver: DatagramChannel):String {

            val message = receiveMessage(receiver)
            return message
//            println(" - Message: $message")
//            receiver.close()
//            println("Receiver closed!")
        }
    }
