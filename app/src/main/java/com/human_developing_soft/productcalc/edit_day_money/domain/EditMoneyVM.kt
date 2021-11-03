package com.human_developing_soft.productcalc.edit_day_money.domain

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.human_developing_soft.productcalc.edit_day_money.ui.DayObserver
import com.human_developing_soft.productcalc.edit_day_money.ui.DayUpdating
import com.human_developing_soft.productcalc.edit_day_money.ui.MoneyDay
import com.human_developing_soft.productcalc.product_storage.domain.DayDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class EditMoneyVM(
    private val mDayId: Int,
    private val mObserver: DayObserver,
    private val mUpdating: DayUpdating,
    context: Context
) : ViewModel() {
    private val mData = EditMoneyUseCase(context)

    fun fetchDay() {
        viewModelScope.launch {
            val day = mData.dayById(mDayId)
            withContext(Dispatchers.Main) {
                mObserver.onDayObtain(
                    day.map(MoneyDay())
                )
            }
        }
    }

    fun updateDay(money: Int) {
        viewModelScope.launch {
            mData.updateDay(
                DayDomain.Base(mDayId, money),
                Date().time
            )
            mUpdating.onDayUpdated()
        }
    }
}