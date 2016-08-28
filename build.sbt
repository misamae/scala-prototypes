name := "scala-junk"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe"            % "config"          % "1.2.1",
  "com.typesafe.akka"       %% "akka-actor"     % "2.4.2",
  "com.typesafe.play"       %% "play"           % "2.5.1",
  "com.typesafe.play"       %% "play-json"      % "2.5.1",
  "org.scalatest"           %% "scalatest"      % "2.2.6" % "test",
  "org.reactivemongo"       %% "reactivemongo"  % "0.11.14",
  "com.amazonaws"           %  "aws-java-sdk"   % "1.11.24",
  "org.apache.poi"          % "poi-ooxml"       % "3.14",
  "org.apache.lucene"       % "lucene-core"     % "6.1.0",
  "org.apache.pdfbox"       % "pdfbox"          % "2.0.2"

)