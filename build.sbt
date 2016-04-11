name := "untitled"

version := "1.0"

scalaVersion := "2.11.7"


// for logging
libraryDependencies ++= Seq(
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
  "ch.qos.logback" % "logback-classic" % "1.1.7"
)

// for test
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.6" % "test",
  "junit" % "junit" % "4.12" % "test",
  "org.hamcrest" % "hamcrest-library" % "1.3" % "test"
)