name := "ScalaFP"

version := "0.1.0"

scalaVersion := "2.11.1"

scalacOptions ++= Seq("-unchecked", "-deprecation")

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.3"

libraryDependencies += "com.typesafe.akka" %% "akka-remote" % "2.3.3"

libraryDependencies <+= scalaVersion(v => v match {  
  case "2.10.2" => "org.scalatest" %% "scalatest" % "1.9" % "test"  
  case "2.10.3" => "org.scalatest" % "scalatest_2.10" % "2.0.RC2" % "test"
  case "2.11.1" => "org.scalatest" % "scalatest_2.11" % "2.1.7" % "test"
})

