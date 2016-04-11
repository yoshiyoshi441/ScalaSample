package com.github.yoshiyoshi441

import org.hamcrest.Matchers._
import org.junit.Assert._
import org.scalatest.{BeforeAndAfterEach, FunSuite}

/**  ArrayTest
  *
  * Created by yoshiyoshi441 on 2016/04/08.
  * https://github.com/yoshiyoshi441
  */
class ArrayTest extends FunSuite with BeforeAndAfterEach {
  /*
    テスト用の配列
   */
  val ary: Array[String] = Array("one", "two", "three")

  override def beforeEach(): Unit = {
    println("setUp")
  }

  override def afterEach(): Unit = {
    println("tearDown")
  }

  test("存在する場合はその値が返る") {
    val result = ary.find(i => i == "two")
    assertThat(result.orNull, is("two"))
  }

  test("存在しない場合はnullになる") {
    val result = ary.find(i => i == "four")
    assertThat(result.orNull, nullValue())
  }

  test("存在を確認する") {
    val result = ary.exists(in => in.equals("two"))
    assertThat(result, is(true))
  }

  test("要素が含まれる") {
    val result = ary.contains("two")
    assertThat(result, is(true))
  }

  test("要素を更新する") {
    val ary: Array[String] = Array("one", "two", "three")
    ary.update(0, "five")
    assertThat(ary, arrayContaining("five", "two", "three"))
  }

  test("文字列を作成する"){
    val str = ary.mkString
    assertThat(str, is("onetwothree"))
  }

  test("カンマ区切りの文字列を作成する") {
    val str = ary.mkString(",")
    assertThat(str, is("one,two,three"))
  }
}
