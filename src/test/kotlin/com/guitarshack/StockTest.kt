package com.guitarshack


import org.junit.jupiter.api.Test
import java.util.UUID
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class StockTest {
    @Test
    fun `should say available only when item on stock`() {
        val item = Item(UUID.randomUUID().toString())
        val itemNotOnStock = Item(UUID.randomUUID().toString())
        val stock = Stock(listOf(item))

        assertTrue(stock.isAvailable(item))
        assertFalse(stock.isAvailable(itemNotOnStock))
    }
}
