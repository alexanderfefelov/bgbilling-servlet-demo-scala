package com.github.alexanderfefelov.bgbilling.servlet.demo

import org.apache.log4j.Logger
import ru.bitel.common.logging.NestedContext

import javax.servlet._
import javax.servlet.http.HttpServletResponse

class TerryPratchettFilterScala extends Filter {

  override def init(filterConfig: FilterConfig): Unit = wrap {
    logger.trace("init")
  }

  override def destroy(): Unit = wrap {
    logger.trace("destroy")
  }

  override def doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain): Unit = wrap {
    logger.trace("doFilter")

    chain.doFilter(request, response)
    response.asInstanceOf[HttpServletResponse].addHeader("X-Clacks-Overhead", "GNU Terry Pratchett")
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
