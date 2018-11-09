package com.AdventureGame

import java.io.ByteArrayInputStream

import org.scalatest.{FunSpec, Matchers}

class AdvTest extends FunSpec with Matchers {

  describe("The Adv object") {

    it("should ask the first question and parse result") {
      val args: Array[String]= Array()
      val in = new ByteArrayInputStream(("end").getBytes)
      Console.withIn(in)
      {
        val result = PlayerService.welcomeToGame(args)
        result shouldBe  "end"
      }
    }
  }
}
