package com.human_developing_sofr.productcalc.history.domain

import org.junit.Test

class DaySeparatingListTest {
    @Test
    fun simpleSeparation() {
        val inputProducts = listOf(
            ProductFactory(2021,
                10, 12
            ).createProduct(),
            ProductFactory(2021,
                10, 13
            ).createProduct()
        )
        val separatedList = DaySeparatingList.Base(
            inputProducts
        ).separate()
        assert(separatedList.size == 2)
    }

    @Test
    fun differentMonth() {
        val inputProducts = listOf(
            ProductFactory(2021,
            10, 12
            ).createProduct(),
            ProductFactory(2021,
            10, 12
            ).createProduct(),
            ProductFactory(2021,
            11, 1
            ).createProduct()
        )
        val separatedList = DaySeparatingList.Base(
            inputProducts
        ).separate()
        assert(separatedList.size == 2)
    }

    @Test
    fun singleDay() {
        val inputProducts = listOf(
            ProductFactory(2021,
            10, 12
            ).createProduct()
        )
        val separatedList = DaySeparatingList.Base(
            inputProducts
        ).separate()
        assert(separatedList.size == 1)
    }
}