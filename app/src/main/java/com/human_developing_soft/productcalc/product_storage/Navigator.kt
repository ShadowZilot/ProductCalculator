package com.human_developing_soft.productcalc.product_storage

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener

interface Navigator {
    fun navigateTo(targetFragment: Class<out Fragment>, data: Bundle? = null)

    fun showDialog(targetFragment: DialogFragment,
             listener: FragmentResultListener? = null,
             data: Bundle? = null)

    fun takeBack()
}
