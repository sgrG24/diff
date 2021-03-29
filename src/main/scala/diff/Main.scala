package diff

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.StrictLogging
import diff.routes.{DiffRoutes, SwaggerRoutes}
import akka.http.scaladsl.server.Directives._
import com.codahale.metrics.health.HealthCheckRegistry
import diff.routes.health.HealthCheckRoutes

import scala.concurrent.Future

trait AkkaServer {
  implicit val system = ActorSystem("Diff")
  implicit val ec = system.dispatcher
  implicit val mat = ActorMaterializer()

  def bind(host: String,
           port: Int,
           routes: Route): Future[Http.ServerBinding] = {
    val server = Http().bindAndHandle(routes, interface = host, port = port)
    sys.ShutdownHookThread {
      mat.shutdown()
      system.terminate()
    }
    server
  }

}

object Main
    extends App
    with DiffRoutes
    with SwaggerRoutes
    with HealthCheckRoutes
    with AkkaServer
    with StrictLogging {

  val registry = new HealthCheckRegistry()
  val routes = diffRoutes ~ swaggerRoutes ~ healthCheckRoutes

  bind("0.0.0.0", 8081, routes)
  logger.info("Server up and running at http://0.0.0.0:8081")
}
