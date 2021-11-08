package com.human_developing_soft.productcalc.product_storage.ui.mapper

import com.human_developing_soft.productcalc.product_storage.ui.ExpenditureUi

class ExpenditureSameId(
    private val mOtherExpenditure: ExpenditureUi
) : ExpenditureUi.Mapper<Boolean> {
    override fun map(id: Int?, name: String,
                     cost: Float,
                     note: String) = id == mOtherExpenditure.map(ExpenditureId())
}