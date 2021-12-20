package com.human_developing_soft.productcalc.main.domain

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.human_developing_soft.productcalc.main.ui.ArraysProvider
import com.human_developing_soft.productcalc.main.ui.FillingResultListener
import com.human_developing_soft.productcalc.product_storage.domain.days
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
class MainViewModel(
    context: Context,
    private val mListener: FillingResultListener
) : ViewModel() {
    private val mAutoFilling = AutoFilling.Base(
        AutoFillUseCase(context),
        ArraysProvider.Base(context)
    )

    fun setupDatabase() {
        viewModelScope.launch(Dispatchers.Main) {
            var startDate = Date().time - (365L * 86400L * 1000L * 2L)
            val currentDate = Date().time
            while (currentDate.days() >= startDate.days()) {
                mAutoFilling.autoFillDay(
                    startDate
                )
                startDate -= 86400000L
            }
            mListener.onFillingComplete()
        }
    }
}