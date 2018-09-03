package sample.orders.dal

import java.sql.{Connection, DriverManager}

class MySQLDriver {
  def connect: Connection = {
    DriverManager.getConnection("jdbc:mysql://localhost/test?user=order_admin&password=1234")
  }

  def createOrder(name: String, amount: Float): Int = {
    val s = connect.prepareStatement("INSERT INTO orders (name, amount) values (?, ?)")
    s.setString(1, name)
    s.setFloat(2, amount)
    s.executeUpdate()
  }
}
