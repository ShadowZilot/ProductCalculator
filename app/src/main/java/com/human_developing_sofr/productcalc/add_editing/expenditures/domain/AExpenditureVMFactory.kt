package com.human_developing_sofr.productcalc.add_editing.expenditures.domain

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.human_developing_sofr.productcalc.add_editing.expenditures.ui.OnExpenditureObserver
import com.human_developing_sofr.productcalc.add_editing.products.domain.OnProductUpdatedListener

class AExpenditureVMFactory(
    private val mId: Int?,
    private val mContext: Context,
    private val mObserver: OnExpenditureObserver,
    private val mListener: OnProductUpdatedListener,
    private val mTime: Long
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AExpenditureVM(
            mId,
            mContext,
            mObserver,
            mListener,
            mTime
        ) as T
    }
}