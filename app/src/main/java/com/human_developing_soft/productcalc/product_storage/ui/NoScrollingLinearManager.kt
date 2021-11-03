package com.human_developing_soft.productcalc.product_storage.ui

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NoScrollingLinearManager(
    context: Context,
    orientation: Int = RecyclerView.VERTICAL,
    reverse: Boolean = false
) : LinearLayoutManager(context, orientation, reverse) {
    override fun canScrollVertically(): Boolean {
        return true
    }
}