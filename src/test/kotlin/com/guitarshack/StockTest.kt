package com.guitarshack


import com.guitarshack.stock.Stock
import org.junit.jupiter.api.Test
import java.util.UUID
import kotlin.test.assertEquals

class StockTest {
    @Test
    fun `should give the number of given item on stock`() {
        val orderItem = OrderItem(UUID.randomUUID().toString())
        val anotherOrderItem = OrderItem(UUID.randomUUID().toString())
        val itemsOnStock = listOf(orderItem, orderItem, orderItem)
        val anotherItemOnStock = listOf(anotherOrderItem, anotherOrderItem)
        val stock = Stock(itemsOnStock + anotherItemOnStock)

        assertEquals(itemsOnStock.size, stock.numberOfAvailableItems(orderItem))
        assertEquals(anotherItemOnStock.size, stock.numberOfAvailableItems(anotherOrderItem))
    }
}

