package com.human_developing_sofr.productcalc.product_storage.domain

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProductListVMFactory(
    private val mObserver: ProductsObserver,
    private val mTime: Long,
    private val mContext: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductsListVM(mObserver, mTime, mContext) as T
    }
}