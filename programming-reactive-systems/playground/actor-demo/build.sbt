ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.7"

val akkaVersion = "2.6.9"

lazy val root = (project in file("."))
  .settings(
    name := "actor-demo"
  )

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion
)
