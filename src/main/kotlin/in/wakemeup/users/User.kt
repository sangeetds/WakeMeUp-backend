package `in`.wakemeup.users

import io.ktor.websocket.DefaultWebSocketSession
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.util.Collections

@Serializable
data class User(
  val id: Long,
  val name: String,
  val email: String,
  var loggedIn: Boolean,
  val session: DefaultWebSocketSession? = null,
  val buddies: List<User> = listOf()
)

val users: MutableMap<String, User> = Collections.synchronizedMap(LinkedHashMap())