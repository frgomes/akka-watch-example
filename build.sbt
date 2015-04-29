name := "akka-watch-example"

version := "1.0"

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor"  % "2.3.4",
  "com.typesafe.akka" %% "akka-remote" % "2.3.4"
)

fork := true