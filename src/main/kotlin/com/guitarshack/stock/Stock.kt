package com.guitarshack.stock


class Stock(private val products: Map<String, UInt> = emptyMap()) {
    fun hasSufficientQuantityOnStock(product: String, desiredQuantity: UInt) = desiredQuantity != 0u && (products[product]?:0u) >= desiredQuantity
}