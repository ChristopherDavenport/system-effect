package io.chrisdavenport.system.effect

import cats.effect.Sync
import cats.implicits._
import scala.util.control.NoStackTrace

object Environment {
  def getEnv[F[_]: Sync](s: String): F[String] = lookupEnv(s)
    .flatMap(_.fold(Sync[F].raiseError[String](EnvironmentVariableDoesNotExist(s)))(_.pure[F]))
    
  def lookupEnv[F[_]: Sync](s: String): F[Option[String]] = Sync[F].delay(sys.env.get(s))

  final case class EnvironmentVariableDoesNotExist(variable: String) extends Throwable with NoStackTrace
}