package com.human_developing_soft.productcalc.product_storage.ui

class IsCollapsed : CollapsingItemUi.Mapper<Boolean> {
    override fun map(type: Int, isCollapsed: Boolean, summa: Int): Boolean {
        return isCollapsed
    }
}