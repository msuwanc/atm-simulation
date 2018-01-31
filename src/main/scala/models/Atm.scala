package models

import utils.Constants

case class Atm(notes: Notes, available_cash: Long) {
  require(available_cash > 0, Constants.AvailableCashInvalidMessage)
}