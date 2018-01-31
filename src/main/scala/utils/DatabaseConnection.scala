package utils

import models.{Atm, Notes}

class DatabaseConnection {
  val notes: Notes = Notes(Configurations.twentyNotes, Configurations.fiftyNotes, Configurations.hundredNotes, Configurations.fiveHundredNotes, Configurations.thousandNotes)

  var atm: Atm = Atm(notes, Calculation.calculateAvailableCash(notes))
}