package com.human_developing_soft.productcalc.product_storage.ui

import androidx.recyclerview.widget.RecyclerView

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
class DirectionScrollListener(
    private val mObserver: OnDirectionChanged
) : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (dy < 0) {
            mObserver.onChanges(OnDirectionChanged.TO_TOP)
        } else if (dy > 0) {
            mObserver.onChanges(OnDirectionChanged.TO_BOTTOM)
        }
    }
}