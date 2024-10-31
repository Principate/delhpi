package com.principate.delphi

import cats.effect.IO
import cats.effect.Resource
import cats.effect.ResourceApp
import natchez.EntryPoint
import natchez.Trace
import natchez.Trace.ioTraceForEntryPoint
import natchez.jaeger.Jaeger
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger
import scribe.Level
import scribe.Scribe
import scribe.cats.*

import bootstrap.{Database, Server, Tracing}
import config.AppConfig

private object Main extends ResourceApp.Forever:

  given Scribe[IO] = scribe
    .Logger("delphi")
    .withMinimumLevel(Level.Trace)
    .f[IO]

  given Logger[IO] = Slf4jLogger.getLoggerFromName("delphi-logger")

  private val configs: Resource[IO, AppConfig] = Resource eval AppConfig[IO]

  override def run(args: List[String]): Resource[IO, Unit] =
    configs.flatMap:
      case AppConfig(postgres) =>
        for
          given Trace[IO] <- Tracing.apply
          postgres        <- Database.connect[IO](
                               postgres.host,
                               postgres.port,
                               postgres.name,
                               postgres.user,
                               postgres.password.value
                             )
          _               <- Server.start[IO]
        yield ()

end Main
