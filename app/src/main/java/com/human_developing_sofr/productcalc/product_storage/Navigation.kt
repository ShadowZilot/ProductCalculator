package com.human_developing_sofr.productcalc.product_storage

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class Navigation private constructor(
    private val mManager: FragmentManager,
    private val mHost: Int
) : Navigator {
    private val mStack = mutableListOf<StackItem>()

    override fun navigateTo(targetFragment: Class<out Fragment>) {
        val transaction = mManager.beginTransaction()
        transaction.replace(
            mHost,
            targetFragment,
            null,
            targetFragment.name
        )
        mStack.add(StackItem.Base(targetFragment, null))
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
        mStack.add(StackItem.Base(targetFragment, data))
        transaction.commit()
    }

    override fun takeBack() {
        if (mStack.size >= 1) {
            val transaction = mManager.beginTransaction()
            mStack.removeAt(mStack.size - 1)
            try {
                transaction.replace(
                    mHost,
                    mStack[mStack.size - 1].fragment(),
                    mStack[mStack.size - 1].args()
                )
                transaction.commit()
            } catch (e : ArrayIndexOutOfBoundsException) {
                e.printStackTrace()
            }
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