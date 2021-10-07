package com.human_developing_sofr.productcalc.product_storage

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class Navigation private constructor(
    private val mManager: FragmentManager,
    private val mHost: Int
) : Navigator {
    override fun navigateTo(targetFragment: Class<out Fragment>) {
        val transaction = mManager.beginTransaction()
        transaction.replace(
            mHost,
            targetFragment,
            Bundle(),
            targetFragment.name
        )
        transaction.commit()
        mManager.popBackStack()
    }

    override fun navigateTo(targetFragment: Class<out Fragment>, data: Bundle) {
        val transaction = mManager.beginTransaction()
        transaction.replace(
            mHost,
            targetFragment,
            data,
            targetFragment.name
        )
        transaction.commit()
        mManager.popBackStack()
    }

    override fun takeBack() {
        val transaction = mManager.beginTransaction()
        val lastIndex = mManager.fragments.lastIndex
        try {
            transaction.replace(
                mHost,
                mManager.fragments[lastIndex - 1]
            )
            transaction.commit()
        } catch (e: IndexOutOfBoundsException) {
            e.printStackTrace()
        }
    }

    object Navigation {
        private var mNavigator : Navigator? = null

        fun instance(manager: FragmentManager, @IdRes host: Int): Navigator {
            mNavigator = Navigation(manager, host)
            return mNavigator!!
        }

        fun instance(): Navigator {
            if (mNavigator == null) {
                throw Exception("Navigation is not initialized yet!")
            } else {
                return mNavigator!!
            }
        }
    }
}