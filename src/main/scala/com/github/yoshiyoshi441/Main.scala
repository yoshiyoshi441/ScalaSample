package com.github.yoshiyoshi441

/**
  * Main
  *
  * Created by yoshiyoshi441 on 2016/04/06.
  * https://github.com/yoshiyoshi441
  */
object Main {
  def main(args: Array[String]): Unit = {
    val message: String = "message"
    var msg: String = "message"
    var point: Point = Person(1, 2)

    println(point.getY)

    val ary: Array[String] = Array("one", "two", "three")
    val result = ary.find(i => i == "four")
    println(result.orNull)
  }
}
