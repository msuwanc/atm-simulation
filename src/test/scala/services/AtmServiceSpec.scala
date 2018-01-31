package services

import daos.AtmDaoImpl
import models._
import org.scalatest.MustMatchers._
import org.scalatest.WordSpec
import utils.{Constants, CustomException, DatabaseConnection}

class AtmServiceSpec extends WordSpec {
  trait AtmServiceFixture {
    val fakeDatabaseConnection = new DatabaseConnection
    val fakeAtmDao = new AtmDaoImpl(fakeDatabaseConnection)
    val atmService = new AtmServiceImpl(fakeAtmDao)

    // Arguments
    val cash = Cash(9999)

    // Results
    // Success
    val note: Notes = Notes(2, 1, 4, 1, 9)
  }
  trait AtmServiceFixtureSuccess extends AtmServiceFixture
  trait AtmServiceFixtureFailure extends AtmServiceFixture {
    override val cash: Cash = Cash(9999999)
  }

  "AtmService" should {
    "ok" when {
      "withdraw while atm still has money" in new AtmServiceFixtureSuccess {
        val result: Either[Exception, Notes] = atmService.withdraw(cash)
        result mustBe Right(note)
      }
    }
    "not ok" when {
      "withdraw but atm has run out of money" in new AtmServiceFixtureFailure {
        val result: Either[Exception, Notes] = atmService.withdraw(cash)
        result mustBe Left(CustomException(s"requirement failed: ${Constants.ThousandNoteInvalidMessage}"))
      }
    }
  }
}