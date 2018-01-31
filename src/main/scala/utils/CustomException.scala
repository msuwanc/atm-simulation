package utils

import akka.http.scaladsl.model.StatusCodes

case class CustomException(message: String = StatusCodes.InternalServerError.reason, code: Int = 500) extends Exception {
  override def getMessage: String = message
}