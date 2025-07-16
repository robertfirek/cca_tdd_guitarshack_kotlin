package com.guitarshack.stock

import com.guitarshack.Item

class Stock(private val items: List<Item> = listOf()) {
    fun numberOfAvailableItems(item: Item): Int = items.count { it.itemIdentifier == item.itemIdentifier }
}