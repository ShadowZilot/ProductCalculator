package com.human_developing_soft.productcalc.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment

interface OnNavigatedListener {
    fun onNavigated(fragment: Class<out Fragment>, data: Bundle?)

    fun onBacked()
}