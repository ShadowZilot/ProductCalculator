package com.human_developing_sofr.productcalc.product_storage

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface Navigator {
    fun navigateTo(targetFragment: Class<out Fragment>)

    fun navigateTo(targetFragment: Class<out Fragment>, data: Bundle)

    fun takeBack()
}
