package com.human_developing_sofr.productcalc.product_storage.domain

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.human_developing_sofr.productcalc.R
import com.human_developing_sofr.productcalc.add_editing.products.ui.AEProductFragment
import com.human_developing_sofr.productcalc.add_editing.selecting_dialog.ui.SelectingDialog
import com.human_developing_sofr.productcalc.edit_day_money.ui.EditingMoneyFragment
import com.human_developing_sofr.productcalc.history.domain.AllDayId
import com.human_developing_sofr.productcalc.history.domain.FreshDayRecognition
import com.human_developing_sofr.productcalc.product_storage.Navigation
import com.human_developing_sofr.productcalc.product_storage.ui.AllDayDomainToUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsListVM(
    private var mObserver: ProductsObserver?,
    private val mTime: Long,
    context: Context
) : ViewModel() {
    private var mDayId : Int? = null
    private val mData : ProductRepository = ProductsUseCase(context)

    fun fetchProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val allDay = mData.dayByDate(mTime)
                mDayId = allDay.map(AllDayId())
                mObserver?.onUpdatedProducts(
                    allDay.map(AllDayDomainToUi())
                )
            } catch (e : DayNotFoundException) {
                mData.createDay(
                    DayDomain.Base(null, 0), mTime
                )
                mObserver?.onError(R.string.empty_products_message)
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
        }
    }

    fun navigateToEditingDay(listener: FragmentResultListener) {
        Navigation.Navigation.instance().showDialog(
            EditingMoneyFragment(),
            listener,
            bundleOf("dayId" to mDayId)
        )
    }

    fun visibilityForAdding(): Int {
        val fresh = FreshDayRecognition.Base(mTime)
        return if (fresh.isFresh()) View.VISIBLE else View.GONE
    }
}