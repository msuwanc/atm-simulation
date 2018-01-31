package daos

import models.{Atm, Notes}

trait AtmDao {
  def remove(notes: Notes): Either[Exception, Atm]
}