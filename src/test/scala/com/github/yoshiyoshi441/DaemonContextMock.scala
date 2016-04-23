package com.github.yoshiyoshi441

import org.apache.commons.daemon.{DaemonContext, DaemonController}

/**
  * DaemonContextMock
  *
  * Created by yoshiyoshi441 on 2016/04/23.
  * https://github.com/yoshiyoshi441
  */
class DaemonContextMock(args: Array[String]) extends DaemonContext {
  override def getController: DaemonController = {
    new DaemonControllerMock
  }

  override def getArguments: Array[String] = {
    args
  }

}

class DaemonControllerMock extends DaemonController {
  override def reload(): Unit = {
    ()
  }

  override def shutdown(): Unit = {
    ()
  }

  override def fail(): Unit = {
    ()
  }

  override def fail(message: String): Unit = {
    ()
  }

  override def fail(exception: Exception): Unit = {
    ()
  }

  override def fail(message: String, exception: Exception): Unit = {
    ()
  }
}
