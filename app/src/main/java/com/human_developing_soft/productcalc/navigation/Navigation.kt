package com.human_developing_soft.productcalc.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.LifecycleOwner

class Navigation private constructor(
    private var mManager: FragmentManager,
    private val mHost: Int,
    private var mHidingNav: OnHidingBottomNav? = null
) : Navigator {
    private val mList = mutableListOf<String>()

    override fun navigateTo(
        targetFragment: Class<out Fragment>,
        data: Bundle?,
        isBackedStack: Boolean
    ) {
        val transaction = mManager.beginTransaction()
        if (!isBackedStack) {
            if (mList.isEmpty()) {
                mList.add("${mList.size}$targetFragment.name{y}")
            } else {
                mList.removeLast()
                mList.add("${mList.size}$targetFragment.name{y}")
            }
            transaction.replace(
                mHost,
                targetFragment,
                data,
                mList.last()
            )
        } else {
            mHidingNav?.onHide()
            mList.add("${mList.size}$targetFragment.name")
            transaction.add(
                mHost,
                targetFragment,
                data,
                mList.last()
            )
        }
        if (mList.size > 1) {
            for (i in mList.size - 2..0) {
                mManager.findFragmentByTag(mList[i])?.onPause()
            }
        }
        transaction.commit()
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
            targetFragment.tag
        )
    }

    override fun takeBack() {
        if (mList.size > 1) {
            val transaction = mManager.beginTransaction()
            transaction.remove(mManager.findFragmentByTag(mList.last())!!)
            mList.remove(mList.last())
            if (mList.last().contains("{y}")) {
                mHidingNav?.onShow()
            }
            mManager.findFragmentByTag(mList.last())!!.onResume()
            transaction.commit()
        } else {
            throw Exception()
        }
    }

    object Navigation {
        private var mNavigator: Navigator? = null

        fun instance(
            manager: FragmentManager,
            @IdRes host: Int,
            hidingNav: OnHidingBottomNav
        ): Navigator {
            mNavigator = Navigation(manager, host, hidingNav)
            return mNavigator!!
        }

        fun instance(): Navigator =
            mNavigator ?: throw Exception("Navigation is not initialized yet!")
    }
}