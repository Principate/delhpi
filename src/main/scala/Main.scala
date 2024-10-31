package com.principate.delphi

import bootstraps.Server

import cats.effect.{IO, Resource, ResourceApp}
import scribe.{Level, Scribe}
import scribe.cats.*

private object Main extends ResourceApp.Forever:

  given Scribe[IO] = scribe
    .Logger("delphi")
    .withMinimumLevel(Level.Trace)
    .f[IO]

  override def run(args: List[String]): Resource[IO, Unit] =
    for _ <- Server.start[IO]
    yield ()

end Main
