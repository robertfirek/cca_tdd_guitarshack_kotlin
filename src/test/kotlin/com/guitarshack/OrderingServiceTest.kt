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
    fun `allows to add order item to the order when the order quantity of product is available on the stock`() {
        val product = UUID.randomUUID().toString()
        val productQuantityOnStock = 10u
        val stock = Stock(products = mapOf(product to productQuantityOnStock))
        val orderItem = OrderItem(product)
        val orderQuantity = 3u
        val orderBefore = Order()

        val order = with(OrderingService(stock)) { orderBefore.addItem(orderItem, orderQuantity) }

        assertEquals(orderQuantity, order.orderItems.size.toUInt())
        assertTrue(order.orderItems.contains(orderItem))
    }

    @Test
    fun `allows to add order item for different products to the order when the corresponding order quantity of product is available on the stock`() {
        val product = UUID.randomUUID().toString()
        val productQuantityOnStock = 10u
        val anotherProduct = UUID.randomUUID().toString()
        val anotherProductQuantityOnStock = 5u
        val stock = Stock(
            products = mapOf(
                product to productQuantityOnStock,
                anotherProduct to anotherProductQuantityOnStock
            )
        )
        val orderItem = OrderItem(product)
        val orderQuantity = 10u
        val anotherOrderItem = OrderItem(anotherProduct)
        val anotherOrderQuantity = 5u
        val orderBefore = Order()

        val order = with(OrderingService(stock)) {
            orderBefore
                .addItem(orderItem, orderQuantity)
                .addItem(anotherOrderItem, anotherOrderQuantity)
        }


        assertEquals(orderQuantity + anotherOrderQuantity, order.orderItems.size.toUInt())
        assertTrue(order.orderItems.contains(orderItem))
        assertTrue(order.orderItems.contains(anotherOrderItem))
    }

    @Test
    fun `do not allow to add order item when the product is unavailable on the stock`() {
        val product = UUID.randomUUID().toString()
        val stock = Stock(products = emptyMap())
        val orderItem = OrderItem(product)
        val orderBefore = Order()

        val orderAfterAddingItem = with(OrderingService(stock)) { orderBefore.addItem(orderItem, 1u) }

        assertEquals(0, orderAfterAddingItem.orderItems.size)
    }

    @Test
    fun `do not allow to add order item when the requested quantity of product is unavailable on the stock`() {
        val product = UUID.randomUUID().toString()
        val productQuantityOnStock = 10u
        val stock = Stock(products = mapOf(product to productQuantityOnStock))
        val orderBefore = Order()
        val orderItem = OrderItem(product)
        val requestedQuantity = productQuantityOnStock + 1u

        val orderAfterAddingItem = with(OrderingService(stock)) { orderBefore.addItem(orderItem, requestedQuantity) }

        assertEquals(0, orderAfterAddingItem.orderItems.size)
    }
}
