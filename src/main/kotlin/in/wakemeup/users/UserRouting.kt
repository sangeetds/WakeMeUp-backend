package `in`.wakemeup.users

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.locations.Location
import io.ktor.server.locations.get
import io.ktor.server.response.respond
import io.ktor.server.routing.Route

@Location("/contact")
private class Contact {

  @Location("/{userId}")
  data class Buddies(val userId: Long, val userName: String)

  @Location("/all")
  class Members
}

fun Route.userRoute() {
  get<Contact.Buddies> { user ->
    val log = call.application.environment.log
    val currentUser = users[user.userName]

    currentUser?.let {
      log.info("Found ${it.buddies.size} buddies for the user ${user.userId}")
      call.respond(HttpStatusCode.OK, it.buddies)
    }

    log.error("User with ${user.userId} not found")
    call.respond(HttpStatusCode.BadRequest)
  }
  get<Contact.Members> {
    val log = call.application.environment.log
    log.info("Returning all members")
    call.respond(users.values.map { it.name })
  }
}
