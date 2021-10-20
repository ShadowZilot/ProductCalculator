package com.human_developing_sofr.productcalc.product_storage.domain

import com.human_developing_sofr.productcalc.product_storage.data.*

class AllDayDataToDomain : AllDay.Mapper<AllDayDomain> {
    override fun map(day: Day, products: List<Product>): AllDayDomain {
        return AllDayDomain.Base(
            day.map(DayDataToDomain()),
            products.map {
                it.map(DataToDomainProduct())
            }
        )
    }
}