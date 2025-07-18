package com.guitarshack


import com.guitarshack.stock.Stock
import org.junit.jupiter.api.Assertions.assertTrue
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

    @Test
    fun `should inform if it has sufficient quantity product`() {
        val product = "Product on the stock"
        val quantityOnStock = 3u
        val desiredQuantity = 3u
        val stock = Stock(products = mapOf(product to quantityOnStock))

        assertTrue(stock.hasSufficientQuantityOnStock(product, desiredQuantity))
    }
}

