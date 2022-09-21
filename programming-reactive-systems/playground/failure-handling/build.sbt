ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.7"

lazy val root = (project in file("."))
  .settings(
    name := "failure-handling"
  )

libraryDependencies ++= {
  Seq("com.typesafe.akka" %% "akka-actor" % "2.6.18")
}