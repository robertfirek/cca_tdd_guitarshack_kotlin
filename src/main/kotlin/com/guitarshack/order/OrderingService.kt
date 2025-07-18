package com.guitarshack.order

import com.guitarshack.OrderItem
import com.guitarshack.stock.Stock

class OrderingService(private val stock: Stock) {
    /**
     * Add orderItem – add an orderItem to an order.
     * An order orderItem has a product and a quantity.
     * There must be sufficient stock of that product to fulfil the order
     */
    fun addItem(orderItem: OrderItem, order: Order, orderQuantity: Int = 1): Order = when {
        hasSufficientItems(orderItem, orderQuantity)
        -> order.copy(orderItems = order.orderItems + (1..orderQuantity).map { orderItem })

        else -> order
    }

    private fun hasSufficientItems(orderItem: OrderItem, orderQuantity: Int) =
        stock.numberOfAvailableItems(orderItem) >= orderQuantity
}