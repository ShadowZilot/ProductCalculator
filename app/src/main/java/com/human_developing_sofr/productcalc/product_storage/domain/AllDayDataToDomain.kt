package com.human_developing_sofr.productcalc.product_storage.domain

import com.human_developing_sofr.productcalc.product_storage.data.*

class AllDayDataToDomain : AllDay.Mapper<AllDayDomain> {
    override fun map(day: Day, products: List<Product>): AllDayDomain {
        val expenditures = mutableListOf<Product>()
        val otherProduct = mutableListOf<Product>()
        for (product in products) {
            if (product.map(CheckingExpenditure())) {
                expenditures.add(product)
            } else {
                otherProduct.add(product)
            }
        }
        return AllDayDomain.Base(
            day.map(DayDataToDomain()),
            otherProduct.map {
                it.map(DataToDomainProduct())
            },
            expenditures.map {
                it.map(ProductToExpenditure())
            }
        )
    }
}