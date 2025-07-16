package com.guitarshack


import com.guitarshack.order.Order
import com.guitarshack.order.OrderingService
import com.guitarshack.stock.Stock
import org.junit.jupiter.api.Test
import java.util.UUID
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class OrderingServiceTest {
    @Test
    fun `allows to add item to the order when the item is available on the stock`() {
        val itemIdentifier = UUID.randomUUID().toString()
        val item = Item(itemIdentifier)
        val stock = Stock(listOf(item))
        val orderingService = OrderingService(stock)
        val orderBeforeAddingItem = Order()

        val orderAfterAddingItem = orderingService.addItem(item, orderBeforeAddingItem)

        assertEquals(1, orderAfterAddingItem.items.size)
        assertTrue(orderAfterAddingItem.items.contains(item))
    }

    @Test
    fun `allows to add multiple items to the order when the required number of items is available on the stock`() {
        val itemIdentifier = UUID.randomUUID().toString()
        val item = Item(itemIdentifier)
        val stockItems = listOf(item, item)
        val stock = Stock(stockItems)
        val orderingService = OrderingService(stock)
        val orderBeforeAddingItem = Order()

        val orderAfterAddingItem = orderingService.addItem(item, orderBeforeAddingItem, stockItems.size)

        assertEquals(stockItems.size, orderAfterAddingItem.items.size)
        assertTrue(orderAfterAddingItem.items.contains(item))
    }

    @Test
    fun `do not allow to add item to the order when the item is unavailable on the stock`() {
        val itemIdentifier = UUID.randomUUID().toString()
        val item = Item(itemIdentifier)
        val stock = Stock()
        val orderingService = OrderingService(stock)
        val orderBeforeAddingItem = Order()

        val orderAfterAddingItem = orderingService.addItem(item, orderBeforeAddingItem)

        assertEquals(0, orderAfterAddingItem.items.size)
    }

    @Test
    fun `do not allow to add item to the order when the requested quantity of items is unavailable on the stock`() {
        val itemIdentifier = UUID.randomUUID().toString()
        val item = Item(itemIdentifier)
        val stock = Stock(listOf(item))
        val orderingService = OrderingService(stock)
        val orderBeforeAddingItem = Order()
        val requestedQuantity = 100

        val orderAfterAddingItem = orderingService.addItem(item, orderBeforeAddingItem, requestedQuantity)

        assertEquals(0, orderAfterAddingItem.items.size)
    }
}
