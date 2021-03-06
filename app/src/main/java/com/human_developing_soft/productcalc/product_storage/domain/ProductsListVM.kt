package com.human_developing_soft.productcalc.product_storage.domain

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.human_developing_soft.productcalc.R
import com.human_developing_soft.productcalc.add_editing.selecting_dialog.ui.SelectingDialog
import com.human_developing_soft.productcalc.edit_day_money.ui.EditingMoneyFragment
import com.human_developing_soft.productcalc.history.domain.AllDayId
import com.human_developing_soft.productcalc.history.domain.FreshDayRecognition
import com.human_developing_soft.productcalc.navigation.Navigation
import com.human_developing_soft.productcalc.product_storage.ui.mapper.AllDayDomainToUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class ProductsListVM(
    private var mObserver: ProductsObserver?,
    time: Long?,
    context: Context
) : ViewModel() {
    private val mTime = time ?: Date().time
    private var mDayId : Int? = null
    private val mData : ProductRepository = ProductsUseCase(context)

    fun fetchProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val allDay = mData.dayByDate(mTime)
                mDayId = allDay.map(AllDayId())
                withContext(Dispatchers.Main) {
                    mObserver?.onUpdatedProducts(
                        allDay.map(AllDayDomainToUi())
                    )
                }
            } catch (e : DayNotFoundException) {
                mData.createDay(
                    DayDomain.Base(null, 0), mTime
                )
                withContext(Dispatchers.Main) {
                    mObserver?.onError(R.string.empty_products_message)
                }
            }
        }
    }

    fun navigateToAdding(id: Int = -1) {
        val fresh = FreshDayRecognition.Base(mTime)
        if (fresh.isFresh()) {
            val args = Bundle()
            args.putInt("id", id)
            args.putLong("time", mTime)
            Navigation.Navigation.instance().showDialog(
                SelectingDialog(),
                null,
                args
            )
        } else {
            mObserver?.onError(R.string.too_old_note, true)
        }
    }

    fun redefineReferences(observer: ProductsObserver) {
        mObserver = observer
    }

    fun navigateToEditingDay(listener: FragmentResultListener) {
        Navigation.Navigation.instance().showDialog(
            EditingMoneyFragment(),
            listener,
            bundleOf("dayId" to mDayId,
            "time" to mTime)
        )
    }

    fun visibilityForAdding(): Int {
        val fresh = FreshDayRecognition.Base(mTime)
        return if (fresh.isFresh()) View.VISIBLE else View.GONE
    }
}