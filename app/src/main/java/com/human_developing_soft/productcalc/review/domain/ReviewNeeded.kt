package com.human_developing_soft.productcalc.review.domain

import com.human_developing_soft.productcalc.review.ui.ReviewContext

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
interface ReviewNeeded {
    fun isReviewNeed(): Boolean

    class Base(

    ) : ReviewContext {
        override fun showReview() {
            TODO("Not yet implemented")
        }
    }
}