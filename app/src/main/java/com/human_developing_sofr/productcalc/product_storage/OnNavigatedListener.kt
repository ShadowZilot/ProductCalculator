package com.human_developing_sofr.productcalc.product_storage

import android.os.Bundle
import androidx.fragment.app.Fragment

interface OnNavigatedListener {
    fun onNavigated(fragment: Class<out Fragment>, data: Bundle?)

    fun onBacked()
}