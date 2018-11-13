package com.AdventureGame

import scala.collection.mutable
import scala.collection.mutable.{HashMap, MultiMap, Set}

final case class StepChoices(optionVal: String, description: String, redirectStep: String)

sealed trait stepDataT {
  val name :String
  val dieOnThisStep: Boolean
  val winOnThisStep: Boolean
  val description: String
}

case class StepData(name :String,
                    dieOnThisStep: Boolean,
                    winOnThisStep: Boolean,
                    description: String) extends stepDataT{
}

trait OrderedMultimap[A, B] extends MultiMap[A, B] {
  override def makeSet: Set[B] = new mutable.LinkedHashSet[B]
}


sealed trait gameObjectT{
  val optionVal: String
  val description : String
}

case class Weapon(optionVal: String, description : String)extends gameObjectT {}
case class OtherObject(optionVal: String, description : String)extends gameObjectT {}
case class Person(optionVal: String, description : String)extends gameObjectT {}
case class Animal(optionVal: String, description : String)extends gameObjectT {}


object AllGameData {

  val gameName = "London Adventure"
  val exitGameCommand = "exit"
  var allLocation = scala.collection.immutable.Map[String, StepData]()
  // https://alvinalexander.com/java/jwarehouse/scala-2.11/library/scala/collection/mutable/MultiMap.scala.shtml
  val allChoice = new HashMap[String, Set[StepChoices]] with OrderedMultimap[String, StepChoices]
  val allLocationObject = new HashMap[String, Set[gameObjectT]] with OrderedMultimap[String, gameObjectT]


  allLocation += ("Buckingham-Palace-gate" -> StepData("buckingham-palace", false, false, "You are standing at the front of Buckingham Palace gates"))
  allChoice.addBinding("Buckingham-Palace-gate", StepChoices("1", "Walk up Constitution Hill", "Constitution-Hill"))
  allChoice.addBinding("Buckingham-Palace-gate", StepChoices("2", "Walk up The Mall", "The-Mall"))
  allChoice.addBinding("Buckingham-Palace-gate", StepChoices("3", "Walk to the Queen Victoria Statue", "Queen-Victoria-Statue"))
  allChoice.addBinding("Buckingham-Palace-gate", StepChoices("4", "Enter Green Park", "Green-Park-buckingham-palace"))
  allChoice.addBinding("Buckingham-Palace-gate", StepChoices("5", "Walk down Spur Road", "Spur-Road"))
  allLocationObject.addBinding("Buckingham-Palace-gate", OtherObject("1", "Crisp packet"))
  allLocationObject.addBinding("Buckingham-Palace-gate", Weapon("1", "Tree branch"))
  allLocationObject.addBinding("Buckingham-Palace-gate", Animal("1", "Horse"))
  allLocationObject.addBinding("Buckingham-Palace-gate", Person("1", "Police person on horseback"))


  allLocation += ("Constitution-Hill" -> StepData("Constitution-Hill", false, false, "You are walking up Constitution Hill"))
  allChoice.addBinding("Constitution-Hill", StepChoices("1", "Walk to Hyde Park Corner", "Hyde-Park-Corner"))
  allChoice.addBinding("Constitution-Hill", StepChoices("2", "Walk to Buckingham Palace", "Buckingham-Palace-gate"))

  allLocation += ("Hyde-Park-Corner" -> StepData("Hyde-Park-Corner", false, false, "You are standing at Hyde Park Corner"))
  allChoice.addBinding("Hyde-Park-Corner", StepChoices("1", "Walk down Constitution Hill", "Constitution-Hill"))
  allChoice.addBinding("Hyde-Park-Corner", StepChoices("2", "Enter Hyde Park", "Hyde-Park-South-East"))

  allLocation += ("Hyde-Park-South-East" -> StepData("Hyde-Park-South-East", false, false, "You are standing in Hyde Park"))
  allChoice.addBinding("Hyde-Park-South-East", StepChoices("1", "Walk through the park towards Marble Arch", "Marble-Arch"))
  allChoice.addBinding("Hyde-Park-South-East", StepChoices("2", "Go to Hyde Park Corner", "Hyde-Park-Corner"))

  allLocation += ("Marble-Arch" -> StepData("Marble-Arch", false, false, "You are standing at Marble Arch"))
  allChoice.addBinding("Marble-Arch", StepChoices("1", "Walk through the park towards Hyde Park Corner", "Hyde-Park-South-East"))
  allChoice.addBinding("Marble-Arch", StepChoices("2", "Walk along Oxford Street", "Oxford-Street-West"))
  allLocationObject.addBinding("Marble-Arch", Animal("1", "Horse"))
  allLocationObject.addBinding("Marble-Arch", Person("2", "Police person on horseback"))




}
