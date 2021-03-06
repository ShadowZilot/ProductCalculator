package com.human_developing_soft.productcalc.history.domain

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.human_developing_soft.productcalc.main.data.SharedPreferencesShell
import com.human_developing_soft.productcalc.history.ui.MonthProvider
import com.human_developing_soft.productcalc.navigation.Navigation
import com.human_developing_soft.productcalc.product_storage.ui.ProductsFragment
import com.human_developing_soft.productcalc.review.domain.ReviewNeeded
import com.human_developing_soft.productcalc.review.ui.ReviewContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryVM(
    context: Context,
    private var mObserver: OnHistoryObtained
) : ViewModel() {
    private val mData = HistoryUseCase(context)

    fun updateData(provider: MonthProvider) {
        viewModelScope.launch(Dispatchers.IO) {
            mObserver.onObtained(
                mData.productHistory().map {
                    it.map(DomainToUiMonth(provider))
                }
            )
        }
    }

    fun goToDay(time: Long) {
        val args = Bundle()
        args.putLong("time", time)
        Navigation.Navigation.instance().navigateTo(
            ProductsFragment::class.java,
            args
        )
    }

    fun redefineReferences(observer: OnHistoryObtained) {
        mObserver = observer
    }

    fun launchReview(shell: SharedPreferencesShell) {
        viewModelScope.launch(Dispatchers.IO) {
            ReviewContext.Internet(
                ReviewContext.Base(ReviewNeeded.Base(
                    shell
                ))
            )
        }
    }
}