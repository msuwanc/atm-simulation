package services

import com.google.inject.Inject
import daos.AtmDao
import models.{Cash, Notes}
import utils.{Calculation, CustomException}

import scala.util.{Failure, Success, Try}

class AtmServiceImpl @Inject()(atmDao: AtmDao) extends AtmService {
  override def withdraw(cash: Cash): Either[Exception, Notes] = {
    Try {
      val reducedNotes: Notes = Calculation.calculateNotesFromCash(cash)
      
      atmDao.remove(reducedNotes).left.map(e => throw e)

      reducedNotes
    } match {
      case Success(reducedNotes) => Right(reducedNotes)
      case Failure(e) => Left(CustomException(e.getMessage))
    }
  }
}