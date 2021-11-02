package com.human_developing_sofr.productcalc.add_editing.selecting_dialog.domain

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SelectingVMFactory(
    private val mContext: Context,
    private val mTime: Long,
    private val mId: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SelectingVM(mContext, mTime, mId) as T
    }
}