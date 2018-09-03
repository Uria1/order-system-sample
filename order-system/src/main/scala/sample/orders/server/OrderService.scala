package sample.orders.server

import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}
import sample.orders.core.OrderRepository
import sample.orders.dal.MySQLDriver

import scala.util.{Failure, Success, Try}

class OrderService extends HttpServlet {

  def repo = new OrderRepository(new MySQLDriver)

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    resp.setContentType("text/html")
    resp.setStatus(HttpServletResponse.SC_OK)

    resp.getWriter.println("<h1>Add New Order</h1>")

    resp.getWriter.println("<form action=\"\" method=\"post\"><table>")
    resp.getWriter.println("<tr><td>Name</td><td><input name=\"name\" /></td></tr>")
    resp.getWriter.println("<tr><td>Amount</td><td><input name=\"amount\" /></td></tr>")
    resp.getWriter.println("<tr><td></td><td><input type=\"submit\" value=\"Add\"/></td></tr>")
    resp.getWriter.println("</table></form>")

    val orders = repo.listOrders
    resp.getWriter.println("<h1>Order List</h1>")
    resp.getWriter.println("<table cellpadding=\"3\" rules=\"all\" style=\"border: 1px solid black\">")
    resp.getWriter.println("<tr><th>Order #</th><th>Name</th><th>Amount</th></tr>")

    orders.foreach(order => {
      resp.getWriter.println(s"<tr><td>${order.id}</td><td>${order.name}</td><td>${order.amount}</td></tr>")
    })

    resp.getWriter.println("</table>")
  }

  override def doPost(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    val name = req.getParameter("name")
    val amount = req.getParameter("amount")

    Try(amount.toFloat) match {
      case Success(fAmount) =>
        val tryCreateOrder = Try {
          repo.createOrder(name, fAmount)
        }

        tryCreateOrder match {
          case Success(_) => resp.sendRedirect("")
          case Failure(e) => showError(e, resp)
        }
      case Failure(e) => showError(new Exception("Amount must be a decimal number"), resp)
    }
  }

  def showError(e: Throwable, resp: HttpServletResponse): Unit = {
    resp.setContentType("text/html")
    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
    resp.getWriter.println("<h1>Error</h1>")
    resp.getWriter.println(e.getMessage)
  }
}
