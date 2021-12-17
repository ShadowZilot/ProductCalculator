package com.human_developing_soft.productcalc.review.ui

import com.human_developing_soft.productcalc.navigation.Navigation
import com.human_developing_soft.productcalc.review.domain.ReviewNeeded
import java.net.InetAddress


/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
interface ReviewContext {
    fun showReview()

    class Internet(
        otherReview: ReviewContext
    ) : ReviewContext {
        init {
            val isInternet = try {
                val ipAdd: InetAddress = InetAddress.getByName("google.com")
                !ipAdd.equals("")
            } catch (e: Exception) {
                false
            }
            if (isInternet) otherReview.showReview()
        }

        override fun showReview() {}
    }

    class Base(
        private val mReviewInfo: ReviewNeeded
    ) : ReviewContext {
        override fun showReview() {
            if (mReviewInfo.isReviewNeed()) {
                Navigation.Navigation.instance().showDialog(
                    ReviewFragment()
                )
            }
        }
    }
}