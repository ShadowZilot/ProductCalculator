package com.human_developing_sofr.productcalc.add_editing.selecting_dialog.domain

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.human_developing_sofr.productcalc.add_editing.expenditures.ui.AExpenditureFragment
import com.human_developing_sofr.productcalc.add_editing.products.ui.AEProductFragment
import com.human_developing_sofr.productcalc.add_editing.selecting_dialog.ui.SelectingDialog
import com.human_developing_sofr.productcalc.product_storage.Navigation
import com.human_developing_sofr.productcalc.product_storage.data.NotExpenditureException
import kotlinx.coroutines.launch
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class SelectingVM(
    context: Context,
    private val mTime: Long,
    private val mID: Int
) : ViewModel() {
    private val mData = SelectingUseCase(context)
    private var mType = 0

    init {
        viewModelScope.launch {
            mType = if (mID == -1) {
                0
            } else {
                try {
                    mData.expenditureById(mID)
                    2
                } catch (e : NotExpenditureException) {
                    1
                }
            }
        }
    }

    fun navigateToSomeFragment(fragmentType: Int) {
        val args = Bundle()
        args.putInt("id", mID)
        args.putLong("time", mTime)
        Log.d(SelectingVM::class.java.simpleName,
            "Selecting type = $mType")
        if (fragmentType == 1) {
            Navigation.Navigation.instance().navigateTo(
                AEProductFragment::class.java,
                args
            )
        } else if (fragmentType == 2) {
            Navigation.Navigation.instance().navigateTo(
                AExpenditureFragment::class.java,
                args
            )
        }
    }

    fun type() = mType
}