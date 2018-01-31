name := "atm-simulation"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies += "com.google.inject" % "guice" % "4.1.0"
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies += "org.mockito" % "mockito-core" % "2.13.0"
libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.0.11"
libraryDependencies += "com.typesafe" % "config" % "1.3.2"