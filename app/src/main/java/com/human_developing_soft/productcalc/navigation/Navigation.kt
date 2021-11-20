package com.human_developing_soft.productcalc.navigation

import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.LifecycleOwner

class Navigation private constructor(
    private var mManager: FragmentManager,
    private val mHost: Int,
) : Navigator {
    private val mList = mutableListOf<String>()

    override fun navigateTo(targetFragment: Class<out Fragment>, data: Bundle?) {
        val transaction = mManager.beginTransaction()
        mList.add("${mList.size}$targetFragment.name")
        transaction.add(
            mHost,
            targetFragment,
            data,
            mList.last(),
        )
        if (mList.size > 1) {
            for (i in mList.size - 2..0) {
                mManager.findFragmentByTag(mList[i])?.onPause()
            }
        }
        transaction.commit()
        Log.d(this::class.java.simpleName,
            "Navigated to ${targetFragment::class.java.simpleName}, size = ${mList.size}")
    }

    override fun showDialog(
        targetFragment: DialogFragment,
        listener: FragmentResultListener?,
        data: Bundle?
    ) {
        targetFragment.arguments = data
        if (listener != null) {
            mManager.setFragmentResultListener(
                "result",
                listener as LifecycleOwner,
                listener
            )
        }
        targetFragment.show(
            mManager,
            targetFragment.tag)
    }

    override fun takeBack() {
        Log.d(this::class.java.simpleName, "Taken back, size = ${mList.size}")
        if (mList.size > 1) {
            val transaction = mManager.beginTransaction()
            transaction.remove(mManager.findFragmentByTag(mList.last())!!)
            mList.remove(mList.last())
            mManager.findFragmentByTag(mList.last())!!.onResume()
            transaction.commit()
        } else {
            throw Exception()
        }
    }

    override fun redefineFragmentManager(fm : FragmentManager) {
        mManager = fm
    }

    object Navigation {
        private var mNavigator: Navigator? = null

        fun instance(
            manager: FragmentManager,
            @IdRes host: Int
        ): Navigator {
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