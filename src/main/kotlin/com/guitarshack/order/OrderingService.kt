package com.guitarshack.order

import com.guitarshack.Item
import com.guitarshack.stock.Stock

class OrderingService(private val stock: Stock) {
    /**
     * Add item – add an item to an order.
     * An order item has a product and a quantity.
     * There must be sufficient stock of that product to fulfil the order
     */
    fun addItem(item: Item, order: Order, orderQuantity: Int = 1): Order = when {
        hasSufficientItems(item, orderQuantity)
        -> order.copy(items = order.items + (1..orderQuantity).map { item })

        else -> order
    }

    private fun hasSufficientItems(item: Item, orderQuantity: Int) =
        stock.numberOfAvailableItems(item) >= orderQuantity
}