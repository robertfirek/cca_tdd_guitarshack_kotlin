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
        val orderQuantity = 3u
        val orderItem = OrderItem(product, orderQuantity)
        val orderBefore = Order()

        val order = with(OrderingService(stock)) { orderBefore.addItem(orderItem) }

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
        val orderQuantity = 10u
        val orderItem = OrderItem(product, orderQuantity)
        val anotherOrderQuantity = 5u
        val anotherOrderItem = OrderItem(anotherProduct, anotherOrderQuantity)
        val orderBefore = Order()

        val order = with(OrderingService(stock)) {
            orderBefore
                .addItem(orderItem)
                .addItem(anotherOrderItem)
        }


        assertEquals(orderQuantity + anotherOrderQuantity, order.orderItems.size.toUInt())
        assertTrue(order.orderItems.contains(orderItem))
        assertTrue(order.orderItems.contains(anotherOrderItem))
    }

    @Test
    fun `do not allow to add order item when the product is unavailable on the stock`() {
        val product = UUID.randomUUID().toString()
        val stock = Stock(products = emptyMap())
        val orderItem = OrderItem(product, 5u)
        val orderBefore = Order()

        val orderAfterAddingItem = with(OrderingService(stock)) { orderBefore.addItem(orderItem) }

        assertEquals(0, orderAfterAddingItem.orderItems.size)
    }

    @Test
    fun `do not allow to add order item when the order quantity of product is unavailable on the stock`() {
        val product = UUID.randomUUID().toString()
        val productQuantityOnStock = 10u
        val stock = Stock(products = mapOf(product to productQuantityOnStock))
        val orderBefore = Order()
        val orderQuantity = productQuantityOnStock + 1u
        val orderItem = OrderItem(product, orderQuantity)

        val orderAfterAddingItem = with(OrderingService(stock)) { orderBefore.addItem(orderItem) }

        assertEquals(0, orderAfterAddingItem.orderItems.size)
    }
}
