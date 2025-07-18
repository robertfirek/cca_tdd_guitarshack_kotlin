package com.guitarshack.stock

import com.guitarshack.OrderItem

class Stock(private val orderItems: List<OrderItem> = listOf(), private val products: Map<String, UInt> = emptyMap()) {
    fun numberOfAvailableItems(orderItem: OrderItem): Int = orderItems.count { it.product == orderItem.product }
    fun hasSufficientQuantityOnStock(product: String, desiredQuantity: UInt) = products[product] == desiredQuantity
}