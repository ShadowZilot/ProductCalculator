package com.human_developing_soft.productcalc.product_storage.ui.mapper

import com.human_developing_soft.productcalc.product_storage.ui.CollapsingItemUi

class CollapsingType : CollapsingItemUi.Mapper<Int> {
    override fun map(type: Int, isCollapsed: Boolean, summa: Int) = type
}