package com.github.yoshiyoshi441

import java.util.concurrent.{ExecutorService, Executors, TimeUnit}

import org.apache.commons.daemon.{Daemon, DaemonContext}

/**
  * HttpEchoServerDaemon
  *
  * Created by yoshiyoshi441 on 2016/04/23.
  * https://github.com/yoshiyoshi441
  */
class HttpEchoServerDaemon extends Daemon {
  /**
    * args
    */
  var args: List[String] = Nil

  /**
    * executor
    */
  val executor: ExecutorService = Executors.newSingleThreadExecutor()

  override def init(context: DaemonContext): Unit = {
    println("init(context)")
    this.args = context.getArguments.toList
  }

  override def destroy(): Unit = {
    println("destroy")
    ()
  }

  override def start(): Unit = {
    println("start server")
    executor.execute(new MainTask(args.toArray))
    ()
  }

  override def stop(): Unit = {
    println("stop server")
    executor.shutdown()
    HttpEchoServer.close()
    if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
      println("not terminated server")
      executor.shutdownNow()
    }
    ()
  }

  private class MainTask(args: Array[String]) extends Runnable {
    override def run(): Unit = {
      HttpEchoServer.main(args)
    }
  }

}

