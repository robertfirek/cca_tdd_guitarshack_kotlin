package com.guitarshack.order

import com.guitarshack.OrderItem
import com.guitarshack.stock.Stock

class OrderingService(private val stock: Stock) {
    /**
     * [Done] Add orderItem – add an orderItem to an order.
     * [Done] An order orderItem has a product and a quantity.
     * [Done] There must be sufficient stock of that product to fulfil the order
     */
    fun Order.addItem(orderItem: OrderItem): Order = when {
        stock.hasSufficientQuantityOnStock(orderItem.product, orderItem.quantity)
        -> this.copy(orderItems = orderItems + orderItem)

        else -> this
    }
}