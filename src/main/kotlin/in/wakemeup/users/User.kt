package `in`.wakemeup.authentication

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
  @Transient val session: DefaultWebSocketSession? = null,
  @Transient val buddies: List<User> = listOf()
)

val users: MutableSet<User> = Collections.synchronizedSet(LinkedHashSet())