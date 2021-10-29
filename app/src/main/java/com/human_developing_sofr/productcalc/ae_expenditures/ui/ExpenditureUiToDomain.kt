package com.human_developing_sofr.productcalc.ae_expenditures.ui

import com.human_developing_sofr.productcalc.product_storage.domain.ExpenditureDomain
import com.human_developing_sofr.productcalc.product_storage.ui.ExpenditureUi

class ExpenditureUiToDomain : ExpenditureUi.Mapper<ExpenditureDomain> {
    override fun map(id: Int, name: String, cost: Float, note: String): ExpenditureDomain {
        return ExpenditureDomain.Base(
            id,
            name,
            cost,
            note
        )
    }
}