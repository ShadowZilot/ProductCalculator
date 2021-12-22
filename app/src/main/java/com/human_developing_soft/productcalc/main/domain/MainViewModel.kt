package com.human_developing_soft.productcalc.main.domain

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.human_developing_soft.productcalc.main.ui.ArraysProvider
import com.human_developing_soft.productcalc.main.ui.FillingResultListener
import com.human_developing_soft.productcalc.product_storage.domain.days
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        viewModelScope.launch(Dispatchers.IO) {
            var endDate = Date().time - (365L * 86400L * 1000L * 1f).toLong()
            val currentDate = Date().time
            while (endDate.days() <= currentDate.days()) {
                mAutoFilling.autoFillDay(
                    endDate
                )
                endDate += 86400L * 1000
            }
            withContext(Dispatchers.Main) {
                mListener.onFillingComplete()
            }
        }
    }
}