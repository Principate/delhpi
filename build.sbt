ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.4"

Global / excludeLintKeys ++= Set(idePackagePrefix)

ThisBuild / libraryDependencies += "com.disneystreaming" %% "weaver-cats" % "0.8.4" % Test
ThisBuild / testFrameworks += new TestFramework("weaver.framework.CatsEffect")

lazy val root = (project in file("."))
  .settings(
    name             := "delphi",
    idePackagePrefix := Some("com.principate.delphi"),
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-effect" % "3.5.5"
    )
  )
  .dependsOn(api, client)
  .aggregate(api, client)

lazy val api = (project in file("app/api"))
  .enablePlugins(Smithy4sCodegenPlugin)
  .settings(
    name             := "delphi-api",
    idePackagePrefix := Some("com.principate.delphi.api"),
    libraryDependencies ++= Seq(
      "com.disneystreaming.smithy4s" %% "smithy4s-http4s"         % smithy4sVersion.value,
      "com.disneystreaming.smithy4s" %% "smithy4s-http4s-swagger" % smithy4sVersion.value,
      "org.http4s"                   %% "http4s-ember-server"     % "0.23.27",
      "com.outr"                     %% "scribe"                  % "3.15.2",
      "com.outr"                     %% "scribe-slf4j2"            % "3.15.2",
      "com.outr"                     %% "scribe-cats"             % "3.15.2"
    )
  )

lazy val client = (project in file("app/client"))
  .settings(
    name             := "delphi-client",
    idePackagePrefix := Some("com.principate.delphi.client"),
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-ember-server" % "0.23.27",
      "org.http4s" %% "http4s-dsl"          % "0.23.27"
    )
  )
