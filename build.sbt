lazy val root = (project in file("."))
  .enablePlugins(ScalaJSPlugin)

// Project Info

name := "Scala.js Google YOLO"

normalizedName := "scalajs-google-yolo"

version := "0.1.0"

organization := "io.medialog"

// Build info

crossScalaVersions in ThisBuild := Seq("2.12.4", "2.10.6", "2.11.11")
// Uncomment this once SBT 1.0.x supports Scala 2.13
//crossScalaVersions in ThisBuild ++= Seq("2.13.0-M2")

scalaVersion in ThisBuild := crossScalaVersions.value.head

scalacOptions ++= Seq("-deprecation", "-feature", "-Xfatal-warnings")

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.3"
)

scalafmtTestOnCompile in ThisBuild := true

// Sonatype publishing info

pomIncludeRepository := { _ => false }

licenses := Seq("MIT" -> url("http://opensource.org/licenses/mit-license.php"))

homepage := Some(url("https://github.com/sloshy/scalajs-google-yolo"))

scmInfo := Some(
  ScmInfo(
    url("https://github.com/sloshy/scalajs-google-yolo"),
    "scm:git@github.com:sloshy/scalajs-google-yolo.git"
  )
)

developers := List(
  Developer(
    id = "sloshy",
    name = "Ryan Peters",
    email = "sloshy42@gmail.com",
    url = url("https://medialog.io")
  )
)

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}