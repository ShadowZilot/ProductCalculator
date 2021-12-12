package com.human_developing_soft.productcalc.history.ui

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
class ListScrollingListener(
    private val mYearObserver: OnYearScrolledListener
) : RecyclerView.OnScrollListener() {
    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        val firstVisible = (recyclerView.layoutManager as LinearLayoutManager)
            .findFirstCompletelyVisibleItemPosition()
        val year = (recyclerView
            .layoutManager
            ?.findViewByPosition(firstVisible) as YearConstraintLayout)
            .year()
        mYearObserver.onYearUpdate(year)
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {}
}