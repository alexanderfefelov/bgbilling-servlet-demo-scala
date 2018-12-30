package com.github.alexanderfefelov.bgbilling.servlet.demo

import org.apache.log4j.Logger
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import ru.bitel.common.logging.NestedContext

class DemoServletScala extends HttpServlet {

  override def init(): Unit = {
    try {
      NestedContext.push(LOG_CONTEXT)
      logger.trace("init")
    } finally {
      NestedContext.pop()
    }
  }

  override def destroy(): Unit = {
    try {
      NestedContext.push(LOG_CONTEXT)
      logger.trace("destroy")
    } finally {
      NestedContext.pop()
    }
  }

  override def doGet(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    try {
      NestedContext.push(LOG_CONTEXT)
      logger.trace("doGet")
      val writer = response.getWriter
      writer.println("Hello, World!")
    } finally {
      NestedContext.pop()
    }
  }

  override def doPost(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    try {
      NestedContext.push(LOG_CONTEXT)
      logger.trace("doPost")
      super.doPost(request, response)
    } finally {
      NestedContext.pop()
    }
  }

  override def doPut(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    try {
      NestedContext.push(LOG_CONTEXT)
      logger.trace("doPut")
      super.doPut(request, response)
    } finally {
      NestedContext.pop()
    }
  }

  override def doDelete(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    try {
      NestedContext.push(LOG_CONTEXT)
      logger.trace("doDelete")
      super.doDelete(request, response)
    } finally {
      NestedContext.pop()
    }
  }

  override def doHead(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    try {
      NestedContext.push(LOG_CONTEXT)
      logger.trace("doHead")
      super.doHead(request, response)
    } finally {
      NestedContext.pop()
    }
  }

  val logger: Logger = Logger.getLogger(this.getClass)

  val LOG_CONTEXT = "servlet"

}
