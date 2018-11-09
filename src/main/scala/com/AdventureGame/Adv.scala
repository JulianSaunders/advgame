package com.AdventureGame

import PlayerService._
import LocationService.gotoFirstLocation

// to run : cd  to project folder, sbt, compile, run
object Adv extends App {
  welcomeToGame(args)
  val playerData = askPlayerName()
  gotoFirstLocation(playerData)
}
