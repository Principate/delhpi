package com.principate.delphi
package bootstrap

import cats.effect.IO
import cats.effect.Resource
import cats.effect.Sync
import natchez.EntryPoint
import natchez.Trace
import natchez.Trace.ioTraceForEntryPoint
import natchez.log.Log
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger

object Tracing:

  def apply(using Logger[IO]): Resource[IO, Trace[IO]] =
    Resource eval ioTraceForEntryPoint(ep)

  private def ep(using Logger[IO]): EntryPoint[IO] =
    Log.entryPoint[IO]("example-service")

end Tracing
