package com.human_developing_soft.productcalc.main.ui

import android.content.Context
import androidx.annotation.ArrayRes

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
interface ArraysProvider {
    fun array(@ArrayRes arrayId: Int): Array<out String>

    class Base(
        private val mContext: Context
    ) : ArraysProvider {
        override fun array(arrayId: Int): Array<String>
            = mContext.resources.getStringArray(arrayId)
    }
}