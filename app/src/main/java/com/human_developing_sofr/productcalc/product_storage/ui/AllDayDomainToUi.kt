package com.human_developing_sofr.productcalc.product_storage.ui

import com.human_developing_sofr.productcalc.product_storage.domain.*

class AllDayDomainToUi : AllDayDomain.Mapper<AllDayUi> {
    override fun map(
        day: DayDomain,
        products: List<DomainProduct>,
        expenditures: List<ExpenditureDomain>
    ): AllDayUi {
        var productSumma = 0f
        for (product in products) {
            productSumma += product.map(DomainSumma())
        }
        return AllDayUi.Base(
            day.map(DayDomainToUi(productSumma.toInt())),
            products.map {
                it.map(DomainToUiProduct())
            },
            expenditures.map {
                it.map(ExpenditureDomainToUi())
            }
        )
    }
}