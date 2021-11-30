package com.human_developing_soft.productcalc.history.domain

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HistoryVMFactory(
    private val mContext: Context,
    private val mObserver: OnHistoryObtained
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HistoryVM(mContext, mObserver) as T
    }
}