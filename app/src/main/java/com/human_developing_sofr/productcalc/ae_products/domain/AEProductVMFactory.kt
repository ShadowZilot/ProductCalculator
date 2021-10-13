package com.human_developing_sofr.productcalc.ae_products.domain

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.util.*

class AEProductVMFactory(
    private val mContext: Context,
    private val mListener: OnProductUpdatedListener,
    private val mListenerProduct: OnProductObtained,
    private val mTime : Long?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val time = mTime ?: Date().time
        return AEProductVM(mContext, mListener, mListenerProduct, time) as T
    }
}