package com.github.yoshiyoshi441

/**
  * HttpEchoServerDaemon
  *
  * Created by yoshiyoshi441 on 2016/04/23.
  * https://github.com/yoshiyoshi441
  */
class HttpEchoServerDaemon extends DaemonBase {
  /**
    * target
    *
    * @return target
    */
  override def target = {
    HttpEchoServer
  }
}

