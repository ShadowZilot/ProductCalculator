package com.human_developing_soft.productcalc.review.ui

import com.human_developing_soft.productcalc.navigation.Navigation
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

    class Base : ReviewContext {
        override fun showReview() {
            // TODO normal rating system
            if ((1..50).random() == 2) {
                Navigation.Navigation.instance().showDialog(
                    ReviewFragment()
                )
            }
        }
    }
}