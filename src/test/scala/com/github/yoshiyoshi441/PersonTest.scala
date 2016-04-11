package com.github.yoshiyoshi441

import org.scalatest.FunSuite
import org.junit.Assert._
import org.hamcrest.CoreMatchers._

/**
  * PersonTest
  *
  * Created by yoshiyoshi441 on 2016/04/11.
  * https://github.com/yoshiyoshi441
  */
class PersonTest extends FunSuite {
  test("Personクラスのテスト") {
    val person = Person(1, 2)
    assertThat(person.getX, is(1))
    assertThat(person.getY, is(2))
  }
}
