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
      val choices: Option[Set[StepChoices]] = allChoice.get(locationId)
      val objects: Option[Set[gameObjectT]] = allLocationObject.get(locationId)


      printLocationDescription(location)
      printStepCount(playerData)
      printChoices(choices)
      printObjects(objects)
      val playerChoice = getUserChoiceInput

      playerChoice match {
        case `exitGameCommand` => goodbye(playerData)
        case _ => {
          choices match {
            case None =>
            case Some(c) => {
              val selectedChoice = c.find(s => s.optionVal == playerChoice.trim.toUpperCase())
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
  }

  def printLocationDescription(location: StepData): Unit = {
    println("***************************************************************")
    fprint(location.description)
  }

  def printStepCount(playerData: PlayerData): Unit = {
    fprint("Steps taken : " + playerData.steps)
  }

  def printChoices(choices: Option[Set[StepChoices]]): Unit = {
    fprint("You have the following options :")
    choices.foreach(c => c.foreach(choice => fprint(choice.optionVal + " : " + choice.description, choices = true)))
  }

  def printObjects(objs: Option[Set[gameObjectT]]): Unit = {
    objs.foreach { objects =>
      if (objects.collect { case o@(_: Weapon | _: OtherObject) => o }.nonEmpty) {
        fprint("There are the following objects that can be picked up :")
        objects.foreach {
          case o: Weapon => fprint("W" + o.optionVal + " : " + o.description, choices = true)
          case o: OtherObject => fprint("O" + o.optionVal + " : " + o.description, choices = true)
          case o@(_: Person | _: Animal) => //I left this in here as an example of how to handle 2 types with 1 command, instead of case _ =>
        }
      }

      if (objects.collect { case o: Person => o }.nonEmpty) {
        fprint("There are the following persons :")
        objects.foreach {
          case o: Person => fprint("P" + o.optionVal + " : " + o.description, choices = true)
          case _ =>
        }
      }

      if (objects.collect { case o: Animal => o }.nonEmpty) {
        fprint("There are the following animals :")
        objects.foreach {
          case o: Animal => fprint("A" + o.optionVal + " : " + o.description, choices = true)
          case _ =>
        }
      }
    }
  }

  def getUserChoiceInput: String = {
    fprint("")
    readLine("*   You >>> ")
  }
}
