package util

import java.time.{Instant, ZoneId, ZonedDateTime}

import scala.util.Try

object IstTime {
  private val IST = ZoneId.of("Asia/Kolkata")

  private def currentInstant(): Instant = Instant.now()
  def now(): ZonedDateTime = currentInstant().atZone(IST)
  def currentMillis(): Long = currentInstant().toEpochMilli

  def fromEpochSeconds(epochSeconds: Long): ZonedDateTime = {
    val i = Instant.ofEpochSecond(epochSeconds)
    ZonedDateTime.ofInstant(i, IST)
  }

  def ymd(epochSeconds: Long): (Int, Int, Int) = {
    val dt = fromEpochSeconds(epochSeconds)
    (dt.getYear, dt.getMonthValue, dt.getDayOfMonth)
  }

  def guessEpochSecs(epoch: Any): Try[Long] =
    Try(epoch.toString.take(10).toLong).filter(_ > 1000000000)
}
