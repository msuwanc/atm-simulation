package models

import utils.Constants

abstract class NoteType {
  def value: Long
}

case class Twenty(value: Long = 20) extends NoteType {
  require(value == 20, Constants.TwentyWrongNoteTypeMessage)
}
case class Fifty(value: Long = 50) extends NoteType {
  require(value == 50, Constants.FiftyWrongNoteTypeMessage)
}
case class Hundred(value: Long = 100) extends NoteType {
  require(value == 100, Constants.HundredWrongNoteTypeMessage)
}
case class FiveHundred(value: Long = 500) extends NoteType {
  require(value == 500, Constants.FiveHundredWrongNoteTypeMessage)
}
case class Thousand(value: Long = 1000) extends NoteType {
  require(value == 1000, Constants.ThousandWrongNoteTypeMessage)
}