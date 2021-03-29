package diff.routes.health

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives.{complete, get, path}
import com.codahale.metrics.health.HealthCheck.Result
import com.codahale.metrics.health.HealthCheckRegistry
import spray.json.DefaultJsonProtocol

import scala.collection.JavaConverters._

trait HealthCheckJsonFormats extends DefaultJsonProtocol {
  implicit val healthCheckResultFormat = jsonFormat3(HealthCheckResult.apply)
}

trait HealthCheckRoutes extends HealthCheckJsonFormats {

  val healthCheckRoutes = path("health") {
    get {
      val results =
        registry.runHealthChecks().asScala.map(HealthCheckResult(_)).toList
      val hasFailed = results.exists(_.status == "NOT OK")
      hasFailed match {
        case true  => complete((StatusCodes.InternalServerError, results))
        case false => complete(results)
      }
    }
  }

  def registry: HealthCheckRegistry
}

case class HealthCheckResult(name: String, status: String, message: String)

object HealthCheckResult {
  def apply(kv: (String, Result)) = {
    val (name, result) = kv
    val status = result.isHealthy match {
      case true  => "OK"
      case false => "NOT OK"
    }
    new HealthCheckResult(name, status, result.getMessage)
  }
}
