package com.human_developing_sofr.productcalc.product_storage.ui

import com.human_developing_sofr.productcalc.product_storage.domain.ExpenditureDomain

class ExpenditureDomainToUi : ExpenditureDomain.Mapper<ExpenditureUi> {
    override fun map(id: Int, name: String,
                     price: Float, note: String): ExpenditureUi {
        return ExpenditureUi.Base(
            id, name, price, note
        )
    }
}