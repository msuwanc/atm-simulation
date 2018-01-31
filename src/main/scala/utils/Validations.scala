package utils

import models.Cash

object Validations {
  def isValidRequest(cash: Cash): Boolean = {
    val listValidValue: List[Long] = List(Constants.TwentyValue, Constants.FiftyValue, Constants.HundredValue, Constants.FiveHundredValue, Constants.ThousandValue)
    val result: Boolean = listValidValue.forall(validValue => (cash.cash % validValue) != 0)

    !result
  }
}