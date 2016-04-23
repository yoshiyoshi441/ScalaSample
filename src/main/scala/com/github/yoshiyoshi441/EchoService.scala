package com.github.yoshiyoshi441

import com.twitter.finagle.Service
import com.twitter.finagle.http._
import com.twitter.finagle.stats.StatsReceiver
import com.twitter.logging._
import com.twitter.util.{Future, Time}

/**
  * EchoServerImpl
  *
  * Created by yoshiyoshi441 on 2016/04/16.
  * https://github.com/yoshiyoshi441
  */
class EchoService(log: Logger, stats: StatsReceiver) extends Service[Request, Response] {
  val countGet = stats.counter("echo/get")
  val countPOST = stats.counter("echo/post")
  val countOther = stats.counter("echo/other")

  /**
    * echo service
    *
    * @param request req
    * @return Future
    */
  override def apply(request: Request): Future[Response] = {
    log.debug("Received a request at " + Time.now)
    request.method match {
      case Method.Get =>
        countGet.incr()
        val response = Response(Version.Http11, Status.Ok)
        response.contentString = "GET Method\n"
        Future.value(response)
      case Method.Post =>
        countPOST.incr()
        val response = Response(Version.Http11, Status.Ok)
        response.contentString = "POST Method\n"
        Future.value(response)
      case _ =>
        countOther.incr()
        val response = Response(Version.Http11, Status.BadRequest)
        response.contentString = "BadRequest\n"
        Future.value(response)
    }
  }
}
