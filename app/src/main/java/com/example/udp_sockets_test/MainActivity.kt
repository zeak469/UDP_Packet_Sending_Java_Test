package com.example.udp_sockets_test

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.IOException
import java.io.PrintWriter
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress


val SERVER_IP = "192.168.1.11"
val SERVER_PORT = 8080
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = findViewById(R.id.button_send)




        button.setOnClickListener {
            // Do something in response to button click
            var s: Thread = Thread(Thread1())
            s.start();
        }
    }

    private var output: PrintWriter? = null
    private var input: BufferedReader? = null
    internal inner class Thread1 : Runnable {
        override fun run() {
            var socket: DatagramSocket
            try {
                val messageStr = "Hello Android!"
                val server_port = SERVER_PORT
                val s = DatagramSocket()
                val local = InetAddress.getByName(SERVER_IP)
                val msg_length = messageStr.length
                val message = messageStr.toByteArray()
                val p = DatagramPacket(message, msg_length, local, server_port)
                s.send(p)
            } catch (e: IOException) {
                System.out.println(e.message)
            }
        }
    }
}