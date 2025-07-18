package com.guitarshack.stock


class Stock(private val products: Map<String, UInt> = emptyMap()) {
    fun hasSufficientQuantityOnStock(product: String, requiredQuantity: UInt) =
        requiredQuantity != 0u && quantityOnStockOf(product) >= requiredQuantity

    private fun quantityOnStockOf(product: String): UInt = products[product] ?: 0u
}