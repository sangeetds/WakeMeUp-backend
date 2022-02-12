package `in`.wakemeup.di

import io.ktor.events.EventDefinition
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationPlugin
import io.ktor.server.application.ApplicationStopping
import io.ktor.util.AttributeKey
import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin

object KoinPlugin : ApplicationPlugin<Application, KoinApplication, Unit> {

  override val key: AttributeKey<Unit>
    get() = AttributeKey("Koin")

  override fun install(
    pipeline: Application,
    configure: KoinApplication.() -> Unit
  ) {
    val monitor = pipeline.environment.monitor
    val koinApplication = startKoin(appDeclaration = configure)
    monitor.raise(EventDefinition(), koinApplication)

    monitor.subscribe(ApplicationStopping) {
      monitor.raise(EventDefinition(), koinApplication)
      stopKoin()
      monitor.raise(EventDefinition(), koinApplication)
    }
  }
}