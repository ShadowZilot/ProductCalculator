package com.human_developing_soft.productcalc.product_storage.ui.mapper

import com.human_developing_soft.productcalc.add_editing.expenditures.domain.WrongExpenditureException
import com.human_developing_soft.productcalc.product_storage.ui.ExpenditureUi

class ExpenditureId : ExpenditureUi.Mapper<Int> {
    override fun map(
        id: Int?, name: String,
        cost: Float,
        note: String
    ) = id ?: throw WrongExpenditureException(
        "Handle expenditure with empty ID"
    )
}