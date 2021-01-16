package com.github.alexanderfefelov.bgbilling.servlet.demo

import org.apache.log4j.Logger
import ru.bitel.common.logging.NestedContext

import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

class HelloWorldScala extends HttpServlet {

  override def init(): Unit = wrap {
    logger.trace("init")
  }

  override def destroy(): Unit = wrap {
    logger.trace("destroy")
  }

  override def doGet(request: HttpServletRequest, response: HttpServletResponse): Unit = wrap {
    logger.trace("doGet")

    val writer = response.getWriter
    writer.println("Hello, World!")
  }

  override def doPost(request: HttpServletRequest, response: HttpServletResponse): Unit = wrap {
    logger.trace("doPost")
    super.doPost(request, response)
  }

  override def doPut(request: HttpServletRequest, response: HttpServletResponse): Unit = wrap {
    logger.trace("doPut")
    super.doPut(request, response)
  }

  override def doDelete(request: HttpServletRequest, response: HttpServletResponse): Unit = wrap {
    logger.trace("doDelete")
    super.doDelete(request, response)
  }

  override def doHead(request: HttpServletRequest, response: HttpServletResponse): Unit = wrap {
    logger.trace("doHead")
    super.doHead(request, response)
  }

  override def doOptions(request: HttpServletRequest, response: HttpServletResponse): Unit = wrap {
    logger.trace("doOptions")
    super.doOptions(request, response)
  }

  override def doTrace(request: HttpServletRequest, response: HttpServletResponse): Unit = wrap {
    logger.trace("doTrace")
    super.doTrace(request, response)
  }

  private def wrap[R](block: => R): R = {
    try {
      NestedContext.push(LOG_CONTEXT)
      block
    } finally {
      NestedContext.pop()
    }
  }

  private val logger = Logger.getLogger(this.getClass)

  private val LOG_CONTEXT = "servlet"

}
