package com.AdventureGame

sealed trait playerDataT{
  val name: String
  val steps: Int
}

case class PlayerData(name: String, steps: Int) extends playerDataT

