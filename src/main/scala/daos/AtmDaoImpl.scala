package daos

import com.google.inject.Inject
import models.{Atm, Notes}
import utils.{Calculation, CustomException, DatabaseConnection}

import scala.util.{Failure, Success, Try}

class AtmDaoImpl @Inject()(databaseConnection: DatabaseConnection) extends AtmDao {
  override def remove(notes: Notes): Either[Exception, Atm] = {
    Try {
      val newAtm: Atm = {
        val newNotes = Calculation.calculateNotes(databaseConnection.atm.notes, notes)
        val newCash = Calculation.calculateAvailableCash(newNotes)

        Atm(newNotes, newCash)
      }

      databaseConnection.atm = newAtm
      
      newAtm
    } match {
      case Success(value) => Right(value)
      case Failure(e) => Left(CustomException(e.getMessage))
    }
  }
}