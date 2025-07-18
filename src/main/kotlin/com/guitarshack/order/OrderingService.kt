package com.guitarshack.order

import com.guitarshack.OrderItem
import com.guitarshack.stock.Stock

class OrderingService(private val stock: Stock) {
    /**
     * Add orderItem – add an orderItem to an order.
     * An order orderItem has a product and a quantity.
     * There must be sufficient stock of that product to fulfil the order
     */
    fun Order.addItem(orderItem: OrderItem, orderQuantity: UInt): Order = when {
        stock.hasSufficientQuantityOnStock(orderItem.product, orderQuantity)
        -> this.copy(orderItems = orderItems + (1..orderQuantity.toLong()).map { orderItem })

        else -> this
    }
}