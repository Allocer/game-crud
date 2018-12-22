name := "game-crud"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.1.5",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.1.5" % Test,
  "com.typesafe.akka" %% "akka-actor" % "2.5.19",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.19" % Test,
  "com.typesafe.akka" %% "akka-stream" % "2.5.19",
  "com.typesafe.akka" %% "akka-stream-testkit" % "2.5.19" % Test,
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.5",
  "org.json4s"        %% "json4s-native"   % "3.5.2",
  "org.json4s"        %% "json4s-ext"      % "3.5.2",
  "de.heikoseeberger" %% "akka-http-json4s" % "1.16.0"
)