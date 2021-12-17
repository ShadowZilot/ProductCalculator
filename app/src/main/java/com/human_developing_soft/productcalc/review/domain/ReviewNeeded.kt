package com.human_developing_soft.productcalc.review.domain

import com.human_developing_soft.productcalc.SharedPreferencesShell
import com.human_developing_soft.productcalc.product_storage.domain.days
import java.util.*

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */

const val sBeginUseApp = "beginUse"
const val sLastReview = "lastReview"

interface ReviewNeeded {
    fun isReviewNeed(): Boolean

    class Base(
        private val mShell: SharedPreferencesShell
    ) : ReviewNeeded {
        override fun isReviewNeed(): Boolean {
            val usedDays = Date().time.days() -
                    mShell.valueLong(sBeginUseApp).days()
            val daysFromLR = Date().time.days() -
                    mShell.valueLong(sLastReview).days()
            return (usedDays >= 4
                    && usedDays % 2 == 0
                    && daysFromLR >= 28
                    && (1..20).random() == 2)
        }
    }
}