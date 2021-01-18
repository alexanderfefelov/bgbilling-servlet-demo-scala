package com.github.alexanderfefelov.bgbilling.servlet.demo

import bitel.billing.common.VersionInfo
import org.apache.log4j.Logger
import ru.bitel.bgbilling.kernel.module.server.ModuleCache
import ru.bitel.common.logging.NestedContext

import java.net.InetAddress
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

import scala.jdk.CollectionConverters._

class SysInfoScala extends HttpServlet {

  override def init(): Unit = wrap {
    logger.trace("init")
  }

  override def destroy(): Unit = wrap {
    logger.trace("destroy")
  }

  override def doGet(request: HttpServletRequest, response: HttpServletResponse): Unit = wrap {
    logger.trace("doGet")

    val writer = response.getWriter
    writer.println(collectSysInfo)
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

  private def collectSysInfo = {
    s"""${collectModules}
      |
      |${collectRuntime}""".stripMargin
  }

  private def collectModules = {
    s"""Modules
      |$HR
      |
      |$inspectKernel
      |$inspectModules""".stripMargin
  }

  private def inspectKernel = {
    val kernelVer = VersionInfo.getVersionInfo("server")
    s"0 ${kernelVer.getModuleName} ${kernelVer.getVersionString}"
  }

  private def inspectModules = {
    ModuleCache.getInstance().getModulesList().asScala.map { module =>
      val ver = VersionInfo.getVersionInfo(module.getName)
      s"${module.getId} ${ver.getModuleName} ${ver.getVersionString}"
    }.mkString(NL)
  }

  private def collectRuntime = {
    s"""Runtime
      |$HR
      |
      |Hostname/IP address: ${InetAddress.getLocalHost}
      |Available processors: ${Runtime.getRuntime.availableProcessors}
      |Memory free / max / total, MB: ${Runtime.getRuntime.freeMemory() / MB} / ${Runtime.getRuntime.maxMemory() / MB} / ${Runtime.getRuntime.totalMemory() / MB}""".stripMargin
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

  private val HR = "--------------------------------------------------"
  private val NL = "\n"
  private val MB = 1024 * 1024

}
