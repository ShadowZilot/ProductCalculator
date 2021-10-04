package com.human_developing_sofr.productcalc.product_storage

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface Navigator {
    fun navigateTo(targetFragment: Class<out Fragment>)

    fun navigateTo(targetFragment: Class<out Fragment>, data: Bundle)

    class Base(
        private val mManager: FragmentManager,
        private val mHost: Int
    ) : Navigator {
        override fun navigateTo(targetFragment: Class<out Fragment>) {
            val transaction = mManager.beginTransaction()
            transaction.replace(
                mHost,
                targetFragment,
                Bundle()
            )
            transaction.commit()
        }

        override fun navigateTo(targetFragment: Class<out Fragment>, data: Bundle) {
            val transaction = mManager.beginTransaction()
            transaction.replace(
                mHost,
                targetFragment,
                data
            )
            transaction.commit()
        }
    }
}