package com.human_developing_soft.productcalc.product_storage.ui.mapper

import com.human_developing_soft.productcalc.product_storage.domain.*
import com.human_developing_soft.productcalc.product_storage.ui.AllDayUi

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
        for (expenditure in expenditures) {
            productSumma += expenditure.map(ExpenditureDomainPrice())
        }
        return AllDayUi.Base(
            day.map(DayDomainToUi(productSumma.toInt())),
            products.reversed().map {
                it.map(DomainToUiProduct())
            },
            expenditures.reversed().map {
                it.map(ExpenditureDomainToUi())
            }
        )
    }
}