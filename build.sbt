name := "example-server"
organization := "com.github.yoshiyoshi441"

version := "1.0"

scalaVersion := "2.11.7"

// resolvers += "Twitter" at "http://maven.twttr.com"

// finagle & thrift
libraryDependencies ++= Seq(
  "org.apache.thrift" % "libthrift" % "0.8.0",
  "com.twitter" %% "scrooge-core" % "4.6.0",
  "com.twitter" %% "finagle-thrift" % "6.34.0"
)

// twitter server & metrics
libraryDependencies ++= Seq(
  "com.twitter" %% "twitter-server" % "1.19.0",
  "com.twitter" %% "finagle-stats" % "6.34.0"
)

// for logging
libraryDependencies ++= Seq(
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
  "ch.qos.logback" % "logback-classic" % "1.1.7"
)

// daemon
libraryDependencies ++= Seq(
  "commons-daemon" % "commons-daemon" % "1.0.15"
)

// for test
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.6" % "test",
  "junit" % "junit" % "4.12" % "test",
  "org.hamcrest" % "hamcrest-library" % "1.3" % "test"
)

enablePlugins(JavaAppPackaging)