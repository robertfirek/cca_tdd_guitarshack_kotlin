package com.guitarshack


import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class OrderingServiceTest {
    @Test
    fun `allows to add item to the order when the item is available on the stock`() {
        val itemIdentifier = "itemIdentifier"
        val item = Item(itemIdentifier)
        val stock = Stock()
        val orderingService = OrderingService(stock)
        val orderBeforeAddingItem = Order()

        val orderAfterAddingItem = orderingService.addItem(item, orderBeforeAddingItem)

        assertEquals(1, orderAfterAddingItem.items.size)
        assertTrue(orderAfterAddingItem.items.contains(item))
    }
}

data class Order( val items: List<Item> = listOf(Item("itemIdentifier")))

class OrderingService(stock: Stock) {
    fun addItem(item: Item, orderBeforeAddingItem: Order):Order =
        orderBeforeAddingItem
}

class Stock

// TODO Do some testing for item.
data class Item(val itemIdentifier: String)
