package com.human_developing_sofr.productcalc

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainVMFactory(
    private val mManager: FragmentManager,
    @IdRes private val mId: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainVM(mManager, mId) as T
    }
}