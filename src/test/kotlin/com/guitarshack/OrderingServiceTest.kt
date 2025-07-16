package com.guitarshack


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
    fun `do not allow to add item to the order when the item is unavailable on the stock`() {
        val itemIdentifier = UUID.randomUUID().toString()
        val item = Item(itemIdentifier)
        val stock = Stock()
        val orderingService = OrderingService(stock)
        val orderBeforeAddingItem = Order()

        val orderAfterAddingItem = orderingService.addItem(item, orderBeforeAddingItem)


        assertEquals(0, orderAfterAddingItem.items.size)
    }
}

data class Order( val items: List<Item> = listOf())

class OrderingService(private val stock: Stock) {
    fun addItem(item: Item, orderBeforeAddingItem: Order):Order = when(stock.isAvailable(item)) {
        true -> orderBeforeAddingItem.copy(items = orderBeforeAddingItem.items + item)
        false -> orderBeforeAddingItem
    }

}

class Stock(private val items: List<Item> = listOf()) {
    fun isAvailable(item: Item): Boolean = items.contains(item)
}

// TODO Do some testing for item.
data class Item(val itemIdentifier: String)
