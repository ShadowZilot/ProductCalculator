package com.human_developing_sofr.productcalc.history.domain

import java.util.*
import kotlin.math.abs

interface FreshDayRecognition {

    fun isFresh(): Boolean

    class Base(
        private val mValidationDays : Long,
        private val mCurrentDays : Long = Date().time
    ) : FreshDayRecognition {

        override fun isFresh(): Boolean {
            return abs(mCurrentDays - mValidationDays) <= 7 * 86400 * 1000
        }
    }
}