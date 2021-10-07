package com.human_developing_sofr.productcalc.product_storage

import android.content.Context
import androidx.annotation.StringRes
import java.util.*

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