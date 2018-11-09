package com.AdventureGame

object Util {

  def fprint(txt: String, newLineFirst: Boolean = true, choices: Boolean = false): Unit = {
    newLineFirst match {
      case true => println("*\n*   " + txt)
      case _ => println("*   " + txt)
    }
  }
}
