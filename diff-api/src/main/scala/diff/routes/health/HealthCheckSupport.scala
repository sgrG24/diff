package diff.routes.health

import nl.grons.metrics.scala.{ByName, HealthCheckMagnet, MetricName}

trait HealthChecksSupport extends nl.grons.metrics.scala.CheckedBuilder {

  private type ToMagnet[T] = ByName[T] => HealthCheckMagnet

  def healthCheckName(name: String) = MetricName(name)

  def checkHealth[T](
    name: String
  )(checker: => T)(implicit toMagnet: ToMagnet[T]) =
    healthCheck(name) { checker }
  def checkHealth[T](name: String, unhealthyMessage: String)(
    checker: => T
  )(implicit toMagnet: ToMagnet[T]) =
    healthCheck(name, unhealthyMessage) { checker }

}
