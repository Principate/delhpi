package com.principate.delphi.api
package handlers

import cats.Applicative

final class HealthCheckHandler[F[_]: Applicative]
   extends CampaignServiceOperation.HealthCheck.Handler[F]:

  override def run(input: Unit): F[HealthCheckOutput] =
    Applicative[F].pure(HealthCheckOutput())

end HealthCheckHandler
