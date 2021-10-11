package com.human_developing_sofr.productcalc.history.domain

import android.content.Context
import androidx.lifecycle.ViewModel
import com.human_developing_sofr.productcalc.history.ui.MonthProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HistoryVM(
    context: Context,
    private val mObserver: OnHistoryObtained
) : ViewModel() {
    private val mData = HistoryUseCase(context)

    fun updateData(provider: MonthProvider) {
        GlobalScope.launch(Dispatchers.IO) {
            mObserver.onObtained(
                mData.productHistory().map {
                    it.map(DomainToUiMonth(provider))
                }
            )
        }
    }

    fun goToDay(time: Long) {

    }
}