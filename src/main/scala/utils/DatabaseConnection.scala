package utils

import models.{Atm, Notes}

class DatabaseConnection {
  val twentyNotes = 5
  val fiftyNotes = 2
  val hundredNotes = 8
  val fiveHundredNotes = 2
  val thousandNotes = 98

  val notes: Notes = Notes(twentyNotes, fiftyNotes, hundredNotes, fiveHundredNotes, thousandNotes)

  var atm: Atm = Atm(notes, Calculation.calculateAvailableCash(notes))
}