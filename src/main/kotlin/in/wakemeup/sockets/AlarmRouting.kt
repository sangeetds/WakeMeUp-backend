package `in`.wakemeup.sockets

import `in`.wakemeup.authentication.User
import `in`.wakemeup.authentication.users
import `in`.wakemeup.common.Message
import io.ktor.server.routing.Route
import io.ktor.server.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import io.ktor.websocket.send
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.util.concurrent.atomic.AtomicLong

fun Route.webSocketRouting() {
  val id = AtomicLong(0)

  webSocket("/alarm") {
    val newUser =
      User(id = id.getAndIncrement(), name = "", email = "", loggedIn = true, session = this)
    users.add(newUser)
    try {
      send("You are connected!")
      for (frame in incoming) {
        frame as? Frame.Text ?: continue
        val receivedText = frame.readText()
        val message = Json.decodeFromString<Message>(receivedText)
        val user = message.user

        users.forEach { connectedUsers ->
          if (connectedUsers.id == user.id) {
            connectedUsers.session?.send(message.toString())
          }
        }
      }
    } catch (e: Exception) {
      println(e.localizedMessage)
    } finally {
      println("Removing $newUser!")
      users -= newUser
    }
  }
}