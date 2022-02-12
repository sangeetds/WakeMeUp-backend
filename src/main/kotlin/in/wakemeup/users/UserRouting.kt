package `in`.wakemeup.users

import `in`.wakemeup.authentication.users
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.locations.Location
import io.ktor.server.routing.Route
import io.ktor.server.locations.get
import io.ktor.server.response.respond

@Location("/contacts")
data class User(val userId: Long) {

  @Location("/buddy")
  data class Buddy(val buddyId: Long)
}

fun Route.userRoute() {
  get<User> { user ->
    val log = call.application.environment.log
    val currentUser = users.firstOrNull { it.id == user.userId }

    currentUser?.let {
      log.info("Found ${it.buddies.size} buddies for the user ${user.userId}")
      call.respond(HttpStatusCode.OK, it.buddies)
    }

    log.error("User with ${user.userId} not found")
    call.respond(HttpStatusCode.BadRequest)
  }
}
