package com.guitarshack


import com.guitarshack.stock.Stock
import org.junit.jupiter.api.Test
import java.util.UUID
import kotlin.test.assertEquals

class StockTest {
    @Test
    fun `should give the number of given item on stock`() {
        val item = Item(UUID.randomUUID().toString())
        val anotherItem = Item(UUID.randomUUID().toString())
        val itemsOnStock = listOf(item, item, item)
        val anotherItemOnStock = listOf(anotherItem, anotherItem)
        val stock = Stock(itemsOnStock + anotherItemOnStock)

        assertEquals(itemsOnStock.size, stock.numberOfAvailableItems(item))
        assertEquals(anotherItemOnStock.size, stock.numberOfAvailableItems(anotherItem))
    }
}

