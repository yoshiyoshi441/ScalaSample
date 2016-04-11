package com.github.yoshiyoshi441

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.FunSuite

/**
  * LoggingTest
  *
  * Created by yoshiyoshi441 on 2016/04/10.
  * https://github.com/yoshiyoshi441
  */
class LoggingTest extends FunSuite with LazyLogging {
  test("ログを書き出すテスト") {
    logger.debug("これはdebugです")
    logger.info("これはinfoです")
    logger.warn("これはwarnです")
    logger.error("これはerrorです")
  }
}
