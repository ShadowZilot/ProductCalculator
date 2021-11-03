package com.human_developing_soft.productcalc.add_editing.selecting_dialog.domain

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.human_developing_soft.productcalc.add_editing.selecting_dialog.ui.OnSelectingTypeGot

class SelectingVMFactory(
    private val mContext: Context,
    private val mListener: OnSelectingTypeGot,
    private val mTime: Long,
    private val mId: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SelectingVM(mContext, mListener, mTime, mId) as T
    }
}