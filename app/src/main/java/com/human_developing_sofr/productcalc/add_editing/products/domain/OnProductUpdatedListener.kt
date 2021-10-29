package com.human_developing_sofr.productcalc.add_editing.products.domain

import androidx.annotation.StringRes

interface OnProductUpdatedListener {
    fun onProductUpdated(@StringRes stringId: Int)

    fun onProductUpdated(message: String)
}