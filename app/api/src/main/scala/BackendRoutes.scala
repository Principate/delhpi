package com.principate.delphi.api

import handlers.HealthCheckHandler
import smithy4s.http4s.SimpleRestJsonBuilder

import cats.Applicative
import cats.effect.{Async, Concurrent, Resource, Sync}
import cats.syntax.all.*
import org.http4s.HttpRoutes

object BackendRoutes:

  def apply[F[_]: Async]: Resource[F, HttpRoutes[F]] =
    serviceRoutes[F].map(_ <+> docs[F])

  private def service[F[_]: Applicative]: CampaignService[F] = CampaignService
    .fromHandlers(
      HealthCheckHandler[F]
    )
    .throwing: CampaignService[F]

  private def serviceRoutes[F[_]: Concurrent]: Resource[F, HttpRoutes[F]] =
    SimpleRestJsonBuilder.routes(service).resource

  private def docs[F[_]: Sync]: HttpRoutes[F] =
    smithy4s.http4s.swagger.docs[F](CampaignService)

end BackendRoutes
