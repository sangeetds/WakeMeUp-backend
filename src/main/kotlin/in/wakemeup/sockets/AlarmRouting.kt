package `in`.wakemeup.sockets

import `in`.wakemeup.users.User
import `in`.wakemeup.users.users
import io.ktor.server.routing.Route
import io.ktor.server.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import io.ktor.websocket.send
import java.util.concurrent.atomic.AtomicLong

fun Route.webSocketRouting() {
  val id = AtomicLong(0)

  webSocket("/alarm/{name}") {
    val name = this.call.parameters["name"] ?: ""
    val newUser =
      User(id = id.getAndIncrement(), name = name, email = "", loggedIn = true, session = this)
    if (users[name] == null) {
      users[name] = newUser
    }
    call.application.environment.log.info("${this.call.request}")
    call.application.environment.log.info("$name has joined")
    try {
      for (frame in incoming) {
        frame as? Frame.Text ?: continue
        val receivedText = frame.readText()
        // val message = Json.decodeFromString<Message>(receivedText)
        // val user = message.user

        users.forEach { (_, connectedUser) ->
          // if (connectedUsers.id == user.id) {
            connectedUser.session?.send(receivedText.toString())
          // }
        }
      }
    } catch (e: Exception) {
      println(e.localizedMessage)
    } finally {
      println("Removing $newUser!")
      users.remove(name)
    }
  }
}