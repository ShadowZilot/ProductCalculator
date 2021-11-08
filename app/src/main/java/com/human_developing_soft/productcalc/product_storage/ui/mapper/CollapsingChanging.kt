package com.human_developing_soft.productcalc.product_storage.ui.mapper

import com.human_developing_soft.productcalc.product_storage.ui.CollapsingItemUi

class CollapsingChanging : CollapsingItemUi.Mapper<CollapsingItemUi> {
    override fun map(type: Int, isCollapsed: Boolean, summa: Int): CollapsingItemUi {
        return CollapsingItemUi.Base(type, !isCollapsed, summa)
    }
}