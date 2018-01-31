package utils

import com.typesafe.config.ConfigFactory

object Configurations {
  val conf = ConfigFactory.load

  val thousandNotes = conf.getLong("notes.thousand")
  val fiveHundredNotes = conf.getLong("notes.five_hundred")
  val hundredNotes = conf.getLong("notes.hundred")
  val fiftyNotes = conf.getLong("notes.fifty")
  val twentyNotes = conf.getLong("notes.twenty")
}