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


publishMavenStyle := true

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

credentials += Credentials(Path.userHome / ".sbt" / "sonatype.sbt")

crossPaths := false

publishArtifact in Test := false

pomIncludeRepository := { repo => false }

pomExtra := (
  <url>https://github.com/YasuOza/play2-redis</url>
  <licenses>
    <license>
      <name>Apache 2.0 License</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:YasuOza/play2-redis.git</url>
    <connection>git@github.com:YasuOza/play2-redis.git</connection>
  </scm>
  <developers>
    <developer>
      <id>yasuoza</id>
      <name>Yasuharu Ozaki</name>
      <url>http://www.yasuoza.com</url>
    </developer>
  </developers>)