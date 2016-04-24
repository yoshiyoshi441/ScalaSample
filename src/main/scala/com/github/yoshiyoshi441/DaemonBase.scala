package com.github.yoshiyoshi441

import java.util.concurrent.{ExecutorService, Executors, TimeUnit}

import com.twitter.server.TwitterServer
import org.apache.commons.daemon.{Daemon, DaemonContext}

/**
  * DaemonBase
  *
  * Created by yoshiyoshi441 on 2016/04/24.
  * https://github.com/yoshiyoshi441
  */
trait DaemonBase extends Daemon {
  /**
    * args
    */
  var args: List[String] = Nil

  /**
    * Executor
    */
  val executor: ExecutorService = Executors.newSingleThreadExecutor()

  /**
    * target object
    *
    * @return target
    */
  protected def target: TwitterServer

  /**
    * init
    * @param context context
    */
  override def init(context: DaemonContext): Unit = {
    println("init(context)")
    args = context.getArguments.toList
  }

  /**
    * destroy
    */
  override def destroy(): Unit = {
    println("destroy")
    ()
  }

  /**
    * start
    */
  override def start(): Unit = {
    println("start server")
    executor.execute(new Runnable {
      override def run(): Unit = {
        target.main(args.toArray)
      }
    })
    ()
  }

  /**
    * stop
    */
  override def stop(): Unit = {
    println("stop server")
    executor.shutdown()
    target.close()
    if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
      println("not terminated server")
      executor.shutdownNow()
    }
    ()
  }

}
