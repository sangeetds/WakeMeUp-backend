package `in`.wakemeup.plugins

import `in`.wakemeup.authentication.userRouting
import `in`.wakemeup.sockets.webSocketRouting
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.StatusPages
import io.ktor.server.response.respond
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Application.configureRouting() {

  routing {
    install(StatusPages) {
      exception<AuthenticationException> { call, _ ->
        call.respond(HttpStatusCode.Unauthorized)
      }
      exception<AuthorizationException> { call, _ ->
        call.respond(HttpStatusCode.Forbidden)
      }
    }

    route("/wakemeup/v1") {
      userRouting()
      webSocketRouting()
    }
  }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()
