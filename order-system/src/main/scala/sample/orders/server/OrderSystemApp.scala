package sample.orders.server

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.{DefaultHandler, HandlerList}
import org.eclipse.jetty.servlet.ServletHandler

object OrderSystemApp {
  def main(args: Array[String]): Unit = {
    val server = new Server(8001)
    val handler = new ServletHandler
    server.setHandler(new HandlerList(handler))
    handler.addServletWithMapping(classOf[OrderService], "/*")
    server.start()
    server.join()
  }
}
