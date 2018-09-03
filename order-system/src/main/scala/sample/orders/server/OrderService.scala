package sample.orders.server

import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

class OrderService extends HttpServlet {

//  def create(name: String, amount: Float) = {
//
//  }

  override def doGet(req: HttpServletRequest, response: HttpServletResponse): Unit ={
    response.setContentType("text/html")
    response.setStatus(HttpServletResponse.SC_OK)
    response.getWriter.println("<h1>Order List</h1>")
  }
}
