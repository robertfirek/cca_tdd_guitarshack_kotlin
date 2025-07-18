package com.guitarshack


import com.guitarshack.stock.Stock
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class StockTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("hasSufficientQuantityOnStockScenarios")
    fun `should inform if it has sufficient quantity product`(
        scenario: String,
        productsOnStock: Map<String, UInt>,
        desiredQuantity: Int,
        expectedHasSufficientQuantityAnswer: Boolean
    ) {
        val stock = Stock(products = productsOnStock)

        assertEquals(
            expectedHasSufficientQuantityAnswer,
            stock.hasSufficientQuantityOnStock(PRODUCT, desiredQuantity.toUInt())
        )
    }

    companion object {
        const val PRODUCT = "Product on the stock"
        const val ANOTHER_PRODUCT = "Another product on the stock"
        val QUANTITY_ON_STOCK = 3u
        const val DESIRED_QUANTITY = 3

        @JvmStatic
        fun hasSufficientQuantityOnStockScenarios(): List<Arguments?> {
            return listOf(
                Arguments.of(
                    "desired quantity is the same as the quantity on the stock",
                    mapOf(PRODUCT to QUANTITY_ON_STOCK),
                    DESIRED_QUANTITY,
                    true
                ),
                Arguments.of(
                    "desired quantity is lower than the quantity on the stock",
                    mapOf(PRODUCT to QUANTITY_ON_STOCK),
                    DESIRED_QUANTITY - 1,
                    true
                ),
                Arguments.of(
                    "the quantity of product on the stock is zero",
                    mapOf(PRODUCT to 0u),
                    DESIRED_QUANTITY,
                    false
                ),
                Arguments.of(
                    "the product is not available in the stock",
                    emptyMap<String, UInt>(),
                    DESIRED_QUANTITY,
                    false
                ),
                Arguments.of(
                    "desired quantity zero",
                    mapOf(PRODUCT to QUANTITY_ON_STOCK),
                    0,
                    false
                ),
                Arguments.of(
                    "multiple products are on the stock",
                    mapOf(
                        PRODUCT to QUANTITY_ON_STOCK,
                        ANOTHER_PRODUCT to DESIRED_QUANTITY - 1
                    ),
                    DESIRED_QUANTITY,
                    true
                ),
            )
        }

    }
}

