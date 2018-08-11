package io.chrisdavenport.system.effect

import cats.effect.Sync
import cats.Show


object Console {
  def putStrLn[F[_]: Sync](s: String): F[Unit] = Sync[F].delay(println(s))
  def print[F[_]: Sync, A: Show](a: A) : F[Unit] = putStrLn[F](Show[A].show(a))

  def getLine[F[_]: Sync] : F[String] = Sync[F].delay(scala.io.StdIn.readLine)
}