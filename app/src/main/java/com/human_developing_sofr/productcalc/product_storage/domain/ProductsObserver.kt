package com.human_developing_sofr.productcalc.product_storage.domain

import androidx.annotation.StringRes
import com.human_developing_sofr.productcalc.product_storage.ui.AllDayUi

interface ProductsObserver {
    fun onUpdatedProducts(day: AllDayUi)

    fun onError(@StringRes stringRes: Int)
}