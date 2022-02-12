package `in`.wakemeup.plugins

import `in`.wakemeup.di.KoinPlugin
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
  // Install Ktor features
  install(KoinPlugin) {
    slf4jLogger()
  }
}