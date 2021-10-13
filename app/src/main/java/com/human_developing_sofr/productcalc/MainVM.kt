package com.human_developing_sofr.productcalc

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.human_developing_sofr.productcalc.product_storage.Navigation

class MainVM(
    manager : FragmentManager,
    @IdRes id : Int
) : ViewModel() {
    private val mNavigator = Navigation.Navigation.instance(
        manager, id
    )

    fun navigateTo(target: Class<out Fragment>) {
        mNavigator.navigateTo(target)
    }

    fun back() {
        mNavigator.takeBack()
    }
}