name := "play2-redis"

version := "0.1.0-SNAPSHOT"

organization := "com.yasuoza"

scalaVersion := "2.10.0"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "play" %% "play" % "2.1.0",
  "play" %% "play-test" % "2.1.0" % "test",
  "net.debasishg" %% "redisclient" % "2.10"
)

