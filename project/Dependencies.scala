import sbt._

object Dependencies {
  val akkaHttpVersion = "10.1.11"
  val akkaStreamVersion = "2.5.29"
  val akkaStack = Seq(
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaStreamVersion,
    "net.virtual-void" %% "json-lenses" % "0.6.2",
    "com.typesafe.akka" %% "akka-testkit" % akkaStreamVersion % Test,
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test
  )
  val scalaMetrics = "nl.grons" %% "metrics-scala" % "4.0.0"
  val akkaHttpSession = "com.softwaremill.akka-http-session" %% "core" % "0.5.11"
  val httpStack = akkaStack ++ Seq(scalaMetrics, akkaHttpSession)

  val scalaTest = "org.scalatest" %% "scalatest" % "3.0.8"
  val mockito = "org.mockito" % "mockito-core" % "3.3.0"

  val testStack = Seq(mockito, scalaTest)

  val diffDependencies = httpStack ++ testStack
}
