package com.human_developing_soft.productcalc.product_storage.ui

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
interface OnDirectionChanged {
    fun onChanges(direction: Int)

    companion object Constants {
        const val TO_BOTTOM = 0
        const val TO_TOP = 1
    }
}