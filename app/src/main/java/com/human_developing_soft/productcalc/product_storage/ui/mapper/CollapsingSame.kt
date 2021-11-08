package com.human_developing_soft.productcalc.product_storage.ui.mapper

import com.human_developing_soft.productcalc.product_storage.ui.CollapsingItemUi

class CollapsingSame(
    private val mOtherCollapsing: CollapsingItemUi
) : CollapsingItemUi.Mapper<Boolean> {
    override fun map(type: Int,
                     isCollapsed: Boolean,
                     summa: Int) = type == mOtherCollapsing.map(CollapsingType())
}