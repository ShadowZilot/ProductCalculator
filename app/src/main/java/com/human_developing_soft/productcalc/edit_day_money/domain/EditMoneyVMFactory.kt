package com.human_developing_soft.productcalc.edit_day_money.domain

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.human_developing_soft.productcalc.edit_day_money.ui.DayObserver
import com.human_developing_soft.productcalc.edit_day_money.ui.DayUpdating

class EditMoneyVMFactory(
    private val mDayId: Int,
    private val mContext: Context,
    private val mObserver: DayObserver,
    private val mUpdating: DayUpdating
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EditMoneyVM(mDayId, mObserver, mUpdating, mContext) as T
    }
}