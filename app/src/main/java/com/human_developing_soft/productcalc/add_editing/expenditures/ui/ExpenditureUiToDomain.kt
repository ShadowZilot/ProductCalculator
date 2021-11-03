package com.human_developing_soft.productcalc.add_editing.expenditures.ui

import com.human_developing_soft.productcalc.product_storage.domain.ExpenditureDomain
import com.human_developing_soft.productcalc.product_storage.ui.ExpenditureUi

class ExpenditureUiToDomain : ExpenditureUi.Mapper<ExpenditureDomain> {
    override fun map(id: Int?, name: String, cost: Float, note: String): ExpenditureDomain {
        return ExpenditureDomain.Base(
            id,
            name,
            cost,
            note
        )
    }
}