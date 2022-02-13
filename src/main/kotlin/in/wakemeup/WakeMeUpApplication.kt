package `in`.wakemeup

import `in`.wakemeup.plugins.configureHTTP
import `in`.wakemeup.plugins.configureKoin
import `in`.wakemeup.plugins.configureLocations
import `in`.wakemeup.plugins.configureMonitoring
import `in`.wakemeup.plugins.configureRouting
import `in`.wakemeup.plugins.configureSerialization
import `in`.wakemeup.plugins.configureSockets
import com.typesafe.config.ConfigFactory
import io.ktor.server.application.port
import io.ktor.server.config.HoconApplicationConfig
import io.ktor.server.engine.embeddedServer
import io.ktor.server.tomcat.Tomcat

fun main() {
  val config = HoconApplicationConfig(ConfigFactory.load())
  embeddedServer(Tomcat, port = config.port, host = "0.0.0.0") {
    configureLocations()
    configureSockets()
    configureRouting()
    configureHTTP()
    configureMonitoring()
    configureSerialization()
    configureKoin()
  }.start(wait = true)
}
