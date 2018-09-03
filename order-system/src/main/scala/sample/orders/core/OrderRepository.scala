package sample.orders.core

import sample.orders.dal.MySQLDriver

class OrderRepository(driver: MySQLDriver) {
  def createOrder(name: String, amount: Float): Int = {
    driver.createOrder(name, amount)
  }
}
