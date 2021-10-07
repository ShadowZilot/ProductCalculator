package com.human_developing_sofr.productcalc.ae_products.domain

import androidx.annotation.StringRes

interface OnProductUpdatedListener {
    fun onProductUpdated(@StringRes stringId: Int)

    fun onProductUpdated(message: String)
}