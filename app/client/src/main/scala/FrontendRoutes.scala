package com.principate.delphi.client

import cats.effect.Async
import org.http4s.*
import org.http4s.server.staticcontent.resourceServiceBuilder

object FrontendRoutes:

  def apply[F[_]: Async]: HttpRoutes[F] =
    resourceServiceBuilder[F]("/").toRoutes

end FrontendRoutes
