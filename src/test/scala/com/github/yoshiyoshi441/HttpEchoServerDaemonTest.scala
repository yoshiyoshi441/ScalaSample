package com.github.yoshiyoshi441

import java.util.concurrent.TimeUnit

import org.scalatest.FunSuite

/**
  * EchoServerDaemonTest
  *
  * Created by yoshiyoshi441 on 2016/04/22.
  * https://github.com/yoshiyoshi441
  */
class HttpEchoServerDaemonTest extends FunSuite {
  test("jsvcを利用する場合のテスト") {
    val args: Array[String] = Array(
      "-admin.port=:8080"
    )
    val daemon: HttpEchoServerDaemon = new HttpEchoServerDaemon()
    val stop: Thread = new Thread() {
      override def run(): Unit = {
        Thread.sleep(2000L)
        daemon.stop()
      }
    }
    stop.start()
    daemon.init(new DaemonContextMock(args))
    daemon.start()
    if (!daemon.executor.awaitTermination(10, TimeUnit.SECONDS)) {
      fail("テスト失敗")
    }
  }
}
