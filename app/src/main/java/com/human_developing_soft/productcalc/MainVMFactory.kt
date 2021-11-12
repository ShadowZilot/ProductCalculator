package com.human_developing_soft.productcalc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.human_developing_soft.productcalc.navigation.Navigator

class MainVMFactory(
    private val mNavigator : Navigator
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainVM(mNavigator) as T
    }
}