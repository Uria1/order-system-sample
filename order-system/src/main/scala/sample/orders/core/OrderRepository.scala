package sample.orders.core

import sample.orders.common.Order
import sample.orders.dal.MySQLDriver

class OrderRepository(driver: MySQLDriver) {
  def listOrders: Seq[Order] = {
    driver.getOrders
  }

  def createOrder(name: String, amount: Float): Int = {
    if (name.isEmpty) throw new Exception("Name must not be empty")
    if (amount <= 0) throw new Exception("Amount must be positive")
    if (amount > 1000) throw new Exception("Amount must not be larger than 1000")
    driver.createOrder(name, amount)
  }
}
