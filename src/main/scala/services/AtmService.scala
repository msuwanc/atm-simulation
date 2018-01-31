package services

import models.{Cash, Notes}

trait AtmService {
  def withdraw(cash: Cash): Either[Exception, Notes]
}