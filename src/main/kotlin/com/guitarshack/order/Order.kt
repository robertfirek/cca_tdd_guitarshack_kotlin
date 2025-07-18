package com.guitarshack.order

import com.guitarshack.OrderItem

data class Order(val orderItems: List<OrderItem> = listOf())