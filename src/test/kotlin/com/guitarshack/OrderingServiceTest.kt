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
        val orderItem = OrderItem(itemIdentifier)
        val stock = Stock(listOf(orderItem))
        val orderingService = OrderingService(stock)
        val orderBeforeAddingItem = Order()

        val orderAfterAddingItem = orderingService.addItem(orderItem, orderBeforeAddingItem)

        assertEquals(1, orderAfterAddingItem.orderItems.size)
        assertTrue(orderAfterAddingItem.orderItems.contains(orderItem))
    }

    @Test
    fun `allows to add multiple items to the order when the required number of items is available on the stock`() {
        val itemIdentifier = UUID.randomUUID().toString()
        val orderItem = OrderItem(itemIdentifier)
        val stockItems = listOf(orderItem, orderItem)
        val stock = Stock(stockItems)
        val orderingService = OrderingService(stock)
        val orderBeforeAddingItem = Order()

        val orderAfterAddingItem = orderingService.addItem(orderItem, orderBeforeAddingItem, stockItems.size)

        assertEquals(stockItems.size, orderAfterAddingItem.orderItems.size)
        assertTrue(orderAfterAddingItem.orderItems.contains(orderItem))
    }

    @Test
    fun `do not allow to add item to the order when the item is unavailable on the stock`() {
        val itemIdentifier = UUID.randomUUID().toString()
        val orderItem = OrderItem(itemIdentifier)
        val stock = Stock()
        val orderingService = OrderingService(stock)
        val orderBeforeAddingItem = Order()

        val orderAfterAddingItem = orderingService.addItem(orderItem, orderBeforeAddingItem)

        assertEquals(0, orderAfterAddingItem.orderItems.size)
    }

    @Test
    fun `do not allow to add item to the order when the requested quantity of items is unavailable on the stock`() {
        val itemIdentifier = UUID.randomUUID().toString()
        val orderItem = OrderItem(itemIdentifier)
        val stock = Stock(listOf(orderItem))
        val orderingService = OrderingService(stock)
        val orderBeforeAddingItem = Order()
        val requestedQuantity = 100

        val orderAfterAddingItem = orderingService.addItem(orderItem, orderBeforeAddingItem, requestedQuantity)

        assertEquals(0, orderAfterAddingItem.orderItems.size)
    }
}
