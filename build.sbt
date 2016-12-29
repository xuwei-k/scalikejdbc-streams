import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import scalariform.formatter.preferences._
import de.johoop.testngplugin.TestNGPlugin._

name := "scalikejdbc-streams"

version := "1.0.0"

scalaVersion := "2.11.8"

crossScalaVersions := Seq("2.11.8", "2.12.1")

organization := "com.github.yoskhdia"

licenses := Seq("Apache-2.0" -> url("https://opensource.org/licenses/Apache-2.0"))

scalacOptions ++= Seq(
  "-language:implicitConversions",
  "-language:postfixOps",
  "-Xfatal-warnings",
  "-Xlint",
  "-feature",
  "-unchecked",
  "-deprecation",
  "-encoding", "utf8",
  "-Ywarn-adapted-args"
)

val scalikeJdbcVersion = "2.5.0"
val reactiveStreamsVersion = "1.0.0"

libraryDependencies ++= Seq(
  "org.reactivestreams" % "reactive-streams" % reactiveStreamsVersion,
  "org.scalikejdbc" %% "scalikejdbc" % scalikeJdbcVersion,
  "ch.qos.logback" % "logback-classic" % "1.1.7" % "test",
  "com.h2database" % "h2" % "1.4.193" % "test",
  "mysql" % "mysql-connector-java" % "5.1.40" % "test",
  "org.apache.derby" % "derby" % "10.13.1.1" % "test",
  "org.hsqldb" % "hsqldb" % "2.3.4" % "test",
  "org.postgresql" % "postgresql" % "9.4.1212" % "test",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "org.reactivestreams" % "reactive-streams-tck" % reactiveStreamsVersion % "test"
)

// Plugins Settings

scalariformSettings

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(PlaceScaladocAsterisksBeneathSecondAsterisk, true)

testNGSettings

testNGVersion := "6.10"
testNGSuites := Seq(((resourceDirectory in Test).value / "testng.xml").absolutePath)
