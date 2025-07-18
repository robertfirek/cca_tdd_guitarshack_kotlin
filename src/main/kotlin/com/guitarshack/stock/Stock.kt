package com.guitarshack.stock

import com.guitarshack.OrderItem

class Stock(private val products: List<OrderItem> = listOf()) {
    fun numberOfAvailableItems(orderItem: OrderItem): Int =
        products.count {
        it.product == orderItem.product
    }
}