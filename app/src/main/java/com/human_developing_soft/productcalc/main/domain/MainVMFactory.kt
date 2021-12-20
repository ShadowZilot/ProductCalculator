package com.human_developing_soft.productcalc.main.domain

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.human_developing_soft.productcalc.main.ui.FillingResultListener

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
class MainVMFactory(
    private val mContext: Context,
    private val mListener: FillingResultListener
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(mContext, mListener) as T
    }
}