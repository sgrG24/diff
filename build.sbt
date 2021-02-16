val scalaTest = "org.scalatest" %% "scalatest" % "3.0.8"
val mockito = "org.mockito" % "mockito-core" % "3.3.0"

lazy val diff = (project in file(".")).settings(
  name := "diff",
  version := "0.1",
  scalaVersion := "2.12.11",
  resolvers += "OSS Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  libraryDependencies ++= Seq(scalaTest, mockito)
)
