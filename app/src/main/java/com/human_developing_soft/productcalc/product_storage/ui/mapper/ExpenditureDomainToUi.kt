package com.human_developing_soft.productcalc.product_storage.ui.mapper

import com.human_developing_soft.productcalc.product_storage.domain.ExpenditureDomain
import com.human_developing_soft.productcalc.product_storage.ui.ExpenditureUi

class ExpenditureDomainToUi : ExpenditureDomain.Mapper<ExpenditureUi> {
    override fun map(id: Int?, name: String,
                     price: Float, note: String): ExpenditureUi {
        return ExpenditureUi.Base(
            id, name, price, note
        )
    }
}