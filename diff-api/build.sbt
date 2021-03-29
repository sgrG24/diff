import Dependencies._

lazy val commonSettings = Seq(
  scalacOptions ++= Seq(
    "-unchecked",
    // stricter code style warnings from compiler
    "-Xlint:unused",
    "-Xlint:infer-any",
    "-Xlint:inaccessible",
    "-Ywarn-dead-code"
  )
)

lazy val diff = (project in file("."))
  .settings(commonSettings: _*)
  .settings(
    name := "diff",
    version := "0.1",
    scalaVersion := "2.12.11",
    resolvers += "OSS Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
    libraryDependencies ++= diffDependencies
  )
