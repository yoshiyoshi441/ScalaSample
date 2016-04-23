logLevel := Level.Warn

resolvers += "twitter-repo" at "https://maven.twttr.com"
resolvers += "Typesafe Releases" at "https://dl.bintray.com/typesafe/maven-releases/"

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.3.5")

addSbtPlugin("com.twitter" % "scrooge-sbt-plugin" % "4.5.0")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.3")

// create rpm package
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.1.0-RC3")
