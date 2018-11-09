package com.AdventureGame

import com.AdventureGame.Util.fprint
import com.AdventureGame.AllGameData._
import com.AdventureGame.PlayerService._

import scala.collection.mutable.Set
import scala.io.StdIn.readLine

object LocationService {
  def gotoFirstLocation(playerData: PlayerData): Unit = {
    printLocationAndGetChoice("Buckingham-Palace-gate", "", playerData)
  }

  def printLocationAndGetChoice(locationId: String, lastLocationId: String, playerData: PlayerData): Unit = {

    if (!allLocation.contains(locationId)) {
      fprint("")
      fprint("Uh oh that location does not exist, sorry about that")
      printLocationAndGetChoice(lastLocationId, lastLocationId, subtractOneStep(playerData))
    }
    else {

      val location: StepData = allLocation(locationId)
      fprint("")
      fprint(location.description)
      fprint("Steps taken : " + playerData.steps)
      fprint("You have the following options :")

      val choices: Set[StepChoices] = allChoice(locationId)
      choices.map(c => fprint(c.optionVal + " : " + c.description, choices = true))

      fprint("")
      val playerChoice = readLine("*   You >>> ")
      playerChoice match {
        case `exitGameCommand` => fprint("Goodbye " + playerData.name + ", thank you for playing")
        case _ => {
          val selectedChoice = choices.find(s => s.optionVal == playerChoice.trim.toUpperCase())
          selectedChoice match {
            case None => {
              fprint("You entered an incorrect value, please try again.\n\n");
              printLocationAndGetChoice(locationId, locationId, playerData)
            }
            case Some(StepChoices(_, _, newLocationId)) => printLocationAndGetChoice(newLocationId, locationId, addOneStep(playerData))
          }
        }
      }
    }
  }
}
