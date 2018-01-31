package models

import utils.Constants

case class Notes(twenty: Long, fifty: Long, hundred: Long, five_hundred: Long, thousand: Long) {
  require(twenty > -1, Constants.TwentyNoteInvalidMessage)
  require(fifty > -1, Constants.FiftyNoteInvalidMessage)
  require(hundred > -1, Constants.HundredNoteInvalidMessage)
  require(five_hundred > -1, Constants.FiveHundredNoteInvalidMessage)
  require(thousand > -1, Constants.ThousandNoteInvalidMessage)
}