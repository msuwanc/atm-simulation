package daos

import models.{Atm, Notes}
import org.mockito.Mockito
import org.scalatest.MustMatchers._
import org.scalatest.WordSpec
import org.scalatest.mockito.MockitoSugar
import utils.{Calculation, Constants, CustomException, DatabaseConnection}

class AtmDaoSpec extends WordSpec with MockitoSugar {
  trait AtmDaoFixture {
    val fakeDatabaseConnection = mock[DatabaseConnection]
    val atmDao = new AtmDaoImpl(fakeDatabaseConnection)

    val twentyNotes = 5
    val fiftyNotes = 2
    val hundredNotes = 8
    val fiveHundredNotes = 2
    val thousandNotes = 98

    val fakeNotes: Notes = Notes(twentyNotes, fiftyNotes, hundredNotes, fiveHundredNotes, thousandNotes)
    val fakeAtm = Atm(fakeNotes, Calculation.calculateAvailableCash(fakeNotes))

    // Arguments
    val reducedNotes: Notes = Notes(1, 1, 1, 1, 1)
    val invalidTwentyReducedNotes: Notes = Notes(100, 0, 0, 0, 0)
    val invalidFiftyReducedNotes: Notes = Notes(0, 100, 0, 0, 0)
    val invalidHundredReducedNotes: Notes = Notes(0, 0, 100, 0, 0)
    val invalidFiveHundredReducedNotes: Notes = Notes(0, 0, 0, 100, 0)
    val invalidThousandReducedNotes: Notes = Notes(0, 0, 0, 0, 100)

    // Results
    // Success
    val newAtm: Atm = {
      val newNotes = Calculation.calculateNotes(fakeNotes, reducedNotes)

      val newCash = Calculation.calculateAvailableCash(newNotes)

      Atm(Calculation.calculateNotes(fakeNotes, reducedNotes), newCash)
    }
  }

  trait AtmDaoFixtureSuccess extends AtmDaoFixture {
    Mockito.when(fakeDatabaseConnection.atm) thenReturn fakeAtm
  }
  trait AtmDaoFixtureFailure extends AtmDaoFixture {
    Mockito.when(fakeDatabaseConnection.atm) thenReturn fakeAtm
  }

  "AtmDao" should {
    "ok" when {
      "remove with valid notes" in new AtmDaoFixtureSuccess {
        val result: Either[Exception, Atm] = atmDao.remove(reducedNotes)
        result mustBe Right(newAtm)
      }
    }
    "not ok" when {
      "remove but atm has run out of twenty notes" in new AtmDaoFixtureFailure {
        val result: Either[Exception, Atm] = atmDao.remove(invalidTwentyReducedNotes)
        result mustBe Left(CustomException(s"requirement failed: ${Constants.TwentyNoteInvalidMessage}"))
      }
      "remove but atm has run out of fifty notes" in new AtmDaoFixtureFailure {
        val result: Either[Exception, Atm] = atmDao.remove(invalidFiftyReducedNotes)
        result mustBe Left(CustomException(s"requirement failed: ${Constants.FiftyNoteInvalidMessage}"))
      }
      "remove but atm has run out of hundred notes" in new AtmDaoFixtureFailure {
        val result: Either[Exception, Atm] = atmDao.remove(invalidHundredReducedNotes)
        result mustBe Left(CustomException(s"requirement failed: ${Constants.HundredNoteInvalidMessage}"))
      }
      "remove but atm has run out of five hundred notes" in new AtmDaoFixtureFailure {
        val result: Either[Exception, Atm] = atmDao.remove(invalidFiveHundredReducedNotes)
        result mustBe Left(CustomException(s"requirement failed: ${Constants.FiveHundredNoteInvalidMessage}"))
      }
      "remove but atm has run out of thousand notes" in new AtmDaoFixtureFailure {
        val result: Either[Exception, Atm] = atmDao.remove(invalidThousandReducedNotes)
        result mustBe Left(CustomException(s"requirement failed: ${Constants.ThousandNoteInvalidMessage}"))
      }
    }
  }
}