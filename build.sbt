ThisBuild / scalaVersion := "2.13.6"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.test.ziofootball"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "ZioFootballRecords",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % "1.0.9",
      "dev.zio" %% "zio-json" % "0.1.5",
      "dev.zio" %% "zio-test" % "1.0.9" % Test,
      "com.softwaremill.sttp.client3" %% "core" % "3.3.6",
      "com.softwaremill.sttp.client3" %% "circe" % "3.3.7",
      "io.circe" %% "circe-generic" % "0.14.1",
      "com.softwaremill.sttp.client3" %% "async-http-client-backend-zio" % "3.3.6"

    ),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )
