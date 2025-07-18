package com.guitarshack.order

import com.guitarshack.OrderItem
import com.guitarshack.stock.Stock

class OrderingService(private val stock: Stock) {
    /**
     * [Done] Add orderItem – add an orderItem to an order.
     * [Done] An order orderItem has a product and a quantity.
     * [Done] There must be sufficient stock of that product to fulfil the order
     */
    fun Order.addItem(orderItem: OrderItem): Order = when {
        stock.hasSufficientQuantityOnStock(orderItem.product, orderItem.quantity)
            // TODO Hide order logic in the Order class:
            //        * make `orderItems` private
            //        * add method to `Order.appendOrderItem`
            //            * take into account that you may order the same product again (updates quantity rather adding a new order item).
        -> this.copy(orderItems = orderItems + orderItem)

        else -> this
    }
}