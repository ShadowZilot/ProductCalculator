package com.human_developing_sofr.productcalc.product_storage

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.human_developing_sofr.productcalc.product_storage.ui.ProductsFragment

class Navigation private constructor(
    private val mManager: FragmentManager,
    private val mHost: Int,
    private val mStack: List<StackItem>,
    private val mListener: OnNavigatedListener
) : Navigator {

    override fun navigateTo(targetFragment: Class<out Fragment>) {
        val transaction = mManager.beginTransaction()
        transaction.replace(
            mHost,
            targetFragment,
            null,
            targetFragment.name
        )
        mListener.onNavigated(targetFragment, null)
        transaction.commit()
    }

    override fun navigateTo(targetFragment: Class<out Fragment>, data: Bundle) {
        val transaction = mManager.beginTransaction()
        transaction.replace(
            mHost,
            targetFragment,
            data,
            targetFragment.name
        )
        mListener.onNavigated(targetFragment, data)
        transaction.commit()
    }

    override fun takeBack() {
        if (mStack.size >= 1) {
            val transaction = mManager.beginTransaction()
            mListener.onBacked()
            transaction.replace(
                mHost,
                mStack[mStack.size - 1].fragment(),
                mStack[mStack.size - 1].args()
            )
            transaction.commit()
        }
    }

    object Navigation {
        private var mNavigator: Navigator? = null

        fun instance(
            manager: FragmentManager,
            @IdRes host: Int,
            stack: List<StackItem>,
            listener: OnNavigatedListener
        ): Navigator {
            mNavigator = Navigation(manager, host, stack, listener)
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