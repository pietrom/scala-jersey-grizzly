package com.example.rest

import org.glassfish.grizzly.websockets.WebSocketApplication
import org.glassfish.grizzly.websockets.WebSocket

class ChatApplication extends WebSocketApplication  {
    override def onMessage(socket: WebSocket, data: String) : Unit = {
        socket.send(data.toUpperCase())
    }
}