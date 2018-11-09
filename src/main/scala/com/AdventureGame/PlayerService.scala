package com.AdventureGame

import com.AdventureGame.AllGameData.gameName
import com.AdventureGame.Util.fprint

import scala.io.StdIn.readLine

object PlayerService {

  def welcomeToGame(args: Array[String]): Unit = {
    if (args.length > 0) {
      fprint("You passed the following args : ")
      for (i <- 0 to args.length - 1) fprint(args(i), false)
    }

    println("\n***************************************************************")
    fprint("Welcome to the game - " + gameName)
  }

  def askPlayerName(): PlayerData = {
    fprint("What is your name?\n*")
    val playerName = readLine("*   You >>> ")
    fprint("Hello " + playerName + ", we hope you have a good time playing \n*\n*")
    new PlayerData(playerName, 0)
  }

  def addOneStep(playerData: PlayerData): PlayerData = {
    new PlayerData(playerData.name, playerData.steps + 1)
  }

  def subtractOneStep(playerData: PlayerData): PlayerData = {
    new PlayerData(playerData.name, playerData.steps - 1)
  }
}
