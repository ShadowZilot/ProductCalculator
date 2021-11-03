package com.human_developing_soft.productcalc.product_storage

import android.os.Bundle
import androidx.fragment.app.Fragment

interface StackItem {
    fun fragment(): Class<out Fragment>

    fun args(): Bundle?

    class Base(
        private val mClass: Class<out Fragment>,
        private val mArgs: Bundle?
    ) : StackItem {
        override fun fragment() = mClass

        override fun args() = mArgs
    }
}