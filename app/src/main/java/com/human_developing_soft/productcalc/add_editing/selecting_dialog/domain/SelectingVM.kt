package com.human_developing_soft.productcalc.add_editing.selecting_dialog.domain

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.human_developing_soft.productcalc.add_editing.expenditures.ui.AExpenditureFragment
import com.human_developing_soft.productcalc.add_editing.products.ui.AEProductFragment
import com.human_developing_soft.productcalc.add_editing.selecting_dialog.ui.OnSelectingTypeGot
import com.human_developing_soft.productcalc.navigation.Navigation
import com.human_developing_soft.productcalc.product_storage.data.NotExpenditureException
import kotlinx.coroutines.launch

class SelectingVM(
    context: Context,
    private val mListener: OnSelectingTypeGot,
    private val mTime: Long,
    private val mID: Int
) : ViewModel() {
    private val mData = SelectingUseCase(context)
    private var mType = -1

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
            mListener.onGot(mType)
        }
    }

    fun navigateToSomeFragment(fragmentType: Int = mType) {
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
}