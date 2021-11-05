package com.human_developing_soft.productcalc.product_storage.ui

class UpdatingSumma(
    private val mNewSumma: Int
) : CollapsingItemUi.Mapper<CollapsingItemUi> {
    override fun map(type: Int, isCollapsed: Boolean, summa: Int): CollapsingItemUi {
        return CollapsingItemUi.Base(type, isCollapsed, mNewSumma)
    }
}