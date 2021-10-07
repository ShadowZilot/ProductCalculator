package com.human_developing_sofr.productcalc.ae_products.domain

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AEProductVMFactory(
    private val mContext: Context,
    private val mListener: OnProductUpdatedListener,
    private val mListenerProduct: OnProductObtained
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AEProductVM(mContext, mListener, mListenerProduct) as T
    }
}