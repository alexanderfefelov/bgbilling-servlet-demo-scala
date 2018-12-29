package com.github.alexanderfefelov.bgbilling.servlet.demo

import org.apache.log4j.Logger
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class DemoServletScala extends HttpServlet {

  override def init(): Unit = {
    logger.trace("init")
  }

  override def destroy(): Unit = {
    logger.trace("destroy")
    super.destroy()
  }

  override def doGet(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    logger.trace("doGet")
    val writer = response.getWriter
    writer.println("Hello, World!")
  }

  override def doPost(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    logger.trace("doPost")
    super.doPost(request, response)
  }

  override def doPut(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    logger.trace("doPut")
    super.doPut(request, response)
  }

  override def doDelete(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    logger.trace("doDelete")
    super.doDelete(request, response)
  }

  override def doHead(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    logger.trace("doHead")
    super.doHead(request, response)
  }

  val logger: Logger = Logger.getLogger(this.getClass)

}
