lazy val root = (project in file("."))
  .settings(
    name := "scalajs-google-yolo",
    version := "0.1.0",
    scalaVersion := "2.12.4",
    organization := "io.medialog",
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.9.1"
    ),
    scalafmtTestOnCompile in ThisBuild := true
  )
  .enablePlugins(ScalaJSPlugin)
