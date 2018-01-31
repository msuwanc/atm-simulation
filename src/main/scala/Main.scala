import com.google.inject.Guice
import models.Cash
import services.AtmService
import utils.{CustomException, Validations}

import scala.io.StdIn

object Main extends App {
  val injector = Guice.createInjector(new Module)
  val atmService = injector.getInstance(classOf[AtmService])

  println("We have many type of notes.(thousand, five hundred, hundred, fifty and twenty)")
  println("You can config them in application.conf file")
  println

  try {
    var flag = true

    while(flag) {
      val wantedCash: Long = StdIn.readLine("How much do you want to withdraw?(positive number without decimal only): ").toLong
      println

      if(Validations.isValidRequest(Cash(wantedCash))) {
        atmService.withdraw(Cash(wantedCash)) match {
          case Left(e) => {
            flag = false

            val message = e.getMessage.replaceAllLiterally("requirement failed: ", "")

            println(message)
          }
          case Right(notes) => {
            val message = s"You've got ${notes.thousand} thousand notes, ${notes.five_hundred} five hundred notes, ${notes.hundred} hundred notes, ${notes.fifty} fifty notes and ${notes.twenty} twenty notes."

            println(message)
            println("Thank you.")
            println
          }
        }
      } else {
        throw CustomException(s"In an ATM with only 20, 50, 100, 500 and 1000 notes, it is not possible to dispense $wantedCash")
      }
    }
  } catch {
    case e: CustomException => println(e.getMessage)
    case e: Exception => println("Positive number without decimal only!")
  }
}