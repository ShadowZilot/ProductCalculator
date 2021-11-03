package com.human_developing_soft.productcalc

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.human_developing_soft.productcalc.product_storage.OnNavigatedListener
import com.human_developing_soft.productcalc.product_storage.StackItem

class MainVM : ViewModel(), OnNavigatedListener {
    private val mStack = mutableListOf<StackItem>()

    override fun onNavigated(fragment: Class<out Fragment>, data: Bundle?) {
        mStack.add(
            StackItem.Base(
                fragment,
                data
            )
        )
    }

    override fun onBacked() {
        mStack.removeAt(mStack.size-1)
    }

    fun stackList() = mStack

    fun restoredList(): StackItem {
        val lastItem = mStack[mStack.size-1]
        mStack.removeAt(mStack.size-1)
        return lastItem
    }
}