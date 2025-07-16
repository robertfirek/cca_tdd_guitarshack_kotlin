package com.guitarshack.order

import com.guitarshack.Item
import com.guitarshack.stock.Stock

class OrderingService(private val stock: Stock) {
    fun addItem(item: Item, orderBeforeAddingItem: Order, orderQuantity: Int = 1): Order = when {
        stock.numberOfAvailableItems(item) >= orderQuantity
        -> orderBeforeAddingItem.copy(items = orderBeforeAddingItem.items + (1..orderQuantity).map { item })

        else -> orderBeforeAddingItem
    }
}