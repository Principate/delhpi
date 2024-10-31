package com.principate.delphi
package config

import cats.effect.Async
import cats.syntax.all.*

case class AppConfig(postgres: PostgresConfig)

object AppConfig:

  def apply[F[_]: Async]: F[AppConfig] =
    PostgresConfig[F].map(AppConfig(_))

end AppConfig
