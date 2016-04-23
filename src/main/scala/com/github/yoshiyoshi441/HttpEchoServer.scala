package com.github.yoshiyoshi441

import java.net.InetSocketAddress

import com.twitter.conversions.time._
import com.twitter.finagle.http.HttpMuxer
import com.twitter.logging.Formatter
import com.twitter.server.TwitterServer
import com.twitter.util.Await

/**
  * HttpEchoServer
  *
  * Created by yoshiyoshi441 on 2016/04/14.
  * https://github.com/yoshiyoshi441
  */
object HttpEchoServer extends TwitterServer {
  val counter = statsReceiver.counter("start")
  val addr = flag("bind", new InetSocketAddress(9999), "Bind address")
  val durations = flag("alarms", (1.second, 5.second), "2 alarm durations")

  override def defaultFormatter = new Formatter(
    timezone = Some("JST"),
    prefix = "<yyyy-MM-dd HH:mm:ss.SSS> [%.3s] %s: "
  )

  override def failfastOnFlagsNotParsed: Boolean = true

  val echoService = new EchoService(log, statsReceiver)

  onExit {
    log.info("stopping server")
    adminHttpServer.close()
  }

  def main(): Unit = {
    log.info("start admin:" + adminHttpServer.boundAddress)
    HttpMuxer.addHandler("/echo", echoService)
    HttpMuxer.addHandler("/echo/", echoService)
    Await.ready(adminHttpServer)
  }
}

