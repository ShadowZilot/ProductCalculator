package com.human_developing_soft.productcalc.product_storage

import android.content.Context
import androidx.annotation.StringRes

interface StringContext {
    fun string(@StringRes id: Int): String

    fun string(@StringRes id: Int, vararg args: Any): String

    class Base(
        private val mContext: Context
    ) : StringContext {

        override fun string(id: Int) = mContext.getString(id)

        override fun string(id: Int, vararg args: Any) = mContext.getString(
            id, *args
        )
    }
}