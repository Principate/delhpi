package com.principate.delphi
package bootstrap

import cats.effect.Async
import cats.effect.Resource
import cats.syntax.all.*
import com.comcast.ip4s.*
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.Router
import org.http4s.server.Server
import org.http4s.server.staticcontent.*

import api.BackendRoutes
import client.FrontendRoutes

object Server:

  def start[F[_]: Async]: Resource[F, Server] =
    for
      httpApp <- routes[F]
      server  <- EmberServerBuilder
                   .default[F]
                   .withHost(ipv4"0.0.0.0")
                   .withPort(port"8080")
                   .withHttpApp(httpApp)
                   .build
    yield server

  private def routes[F[_]: Async] = BackendRoutes[F].map: backendRoutes =>
    Router(
      "/api" -> backendRoutes,
      "/app" -> FrontendRoutes[F]
    ).orNotFound

end Server
