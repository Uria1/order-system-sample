package sample.orders.dal

import java.sql.{Connection, DriverManager}

import sample.orders.common.Order

class MySQLDriver {
  def connect: Connection = {
    DriverManager.getConnection("jdbc:mysql://localhost/order_db?user=order_admin&password=1234")
  }

  def createOrder(name: String, amount: Float): Int = {
    val s = connect.prepareStatement("INSERT INTO orders (name, amount) values (?, ?)")
    s.setString(1, name)
    s.setFloat(2, amount)
    s.executeUpdate()
  }

  def getOrders: Seq[Order] = {
    val s = connect.createStatement
    val rs = s.executeQuery("SELECT id, name, amount FROM orders")
    var results = Seq.empty[Order]

    while (rs.next()) {
      val id = rs.getInt("id")
      val name = rs.getString("name")
      val amount = rs.getFloat("amount")
      results = results :+ Order(id, name, amount)
    }

    results
  }
}
