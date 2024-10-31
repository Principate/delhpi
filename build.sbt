ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.4"

Global / excludeLintKeys ++= Set(idePackagePrefix)

ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision

ThisBuild / libraryDependencies += "com.disneystreaming" %% "weaver-cats" % "0.8.4" % Test
ThisBuild / testFrameworks += new TestFramework("weaver.framework.CatsEffect")

lazy val root = (project in file("."))
  .settings(
    name             := "delphi",
    idePackagePrefix := Some("com.principate.delphi"),
    libraryDependencies ++= Seq(
      "org.typelevel"      %% "cats-effect"                % "3.5.5",
      "is.cir"             %% "ciris"                      % "3.6.0",
      "org.flywaydb"        % "flyway-core"                % "10.15.2",
      "org.flywaydb"        % "flyway-database-postgresql" % "10.15.2" % Runtime,
      "org.tpolecat"       %% "natchez-jaeger"             % "0.3.5",
      "org.tpolecat"       %% "natchez-log"                % "0.3.5",
      "io.github.iltotore" %% "iron"                       % "2.5.0",
      "io.github.iltotore" %% "iron-cats"                  % "2.5.0",
      "io.github.iltotore" %% "iron-circe"                 % "2.6.0",
      "io.github.iltotore" %% "iron-ciris"                 % "2.5.0",
      "io.github.iltotore" %% "iron-skunk"                 % "2.6.0"
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
      "com.outr"                     %% "scribe-slf4j2"           % "3.15.2",
      "com.outr"                     %% "scribe-cats"             % "3.15.2",
      "org.postgresql"                % "postgresql"              % "42.7.3",
      "org.tpolecat"                 %% "skunk-core"              % "0.6.3"
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
