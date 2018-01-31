package utils

import models._

object Calculation {
  def calculateAvailableCash(notes: Notes): Long = {
    (notes.twenty*Constants.TwentyValue)+
      (notes.fifty*Constants.FiftyValue)+
      (notes.hundred*Constants.HundredValue)+
      (notes.five_hundred*Constants.FiveHundredValue)+
      (notes.thousand*Constants.ThousandValue)
  }

  def calculateNotes(currentNotes: Notes, reducedNotes: Notes): Notes = {
    currentNotes.copy(
      currentNotes.twenty - reducedNotes.twenty,
      currentNotes.fifty - reducedNotes.fifty,
      currentNotes.hundred - reducedNotes.hundred,
      currentNotes.five_hundred - reducedNotes.five_hundred,
      currentNotes.thousand - reducedNotes.thousand
    )
  }

  def calculateNotesAndRemains(cashValue: Long, noteType: NoteType): (Long, Long) = {
    def calculateHelper(nodeTypeValue: Long): (Long, Long) = {
      if(cashValue > nodeTypeValue) {
        (cashValue / nodeTypeValue, cashValue % nodeTypeValue)
      } else (0, cashValue)
    }

    noteType match {
      case Twenty(value) => calculateHelper(value)
      case Fifty(value) => calculateHelper(value)
      case Hundred(value) => calculateHelper(value)
      case FiveHundred(value) => calculateHelper(value)
      case Thousand(value) => calculateHelper(value)
    }
  }

  def calculateNotesFromCash(cash: Cash): Notes = {
    val result: Notes = Notes(0, 0, 0, 0, 0)

    val thousandNotesAndRemains: (Long, Long) = calculateNotesAndRemains(cash.cash, Thousand())
    val fiveHundredNotesAndRemains: (Long, Long) = calculateNotesAndRemains(thousandNotesAndRemains._2, FiveHundred())
    val hundredNotesAndRemains: (Long, Long) = calculateNotesAndRemains(fiveHundredNotesAndRemains._2, Hundred())
    val fiftyNotesAndRemains: (Long, Long) = calculateNotesAndRemains(hundredNotesAndRemains._2, Fifty())
    val twentyNotesAndRemains: (Long, Long) = calculateNotesAndRemains(fiftyNotesAndRemains._2, Twenty())

    result.copy(twentyNotesAndRemains._1, fiftyNotesAndRemains._1, hundredNotesAndRemains._1, fiveHundredNotesAndRemains._1, thousandNotesAndRemains._1)
  }
}