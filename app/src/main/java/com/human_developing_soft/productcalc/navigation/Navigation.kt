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
    private val mTagList = mutableListOf<String>()

    init {
        mTagList.addAll(mManager.fragments.map {
            it.tag ?: "Unknown fragment"
        })
    }

    override fun navigateTo(
        targetFragment: Class<out Fragment>,
        data: Bundle?,
        isBackedStack: Boolean
    ) {
        val transaction = mManager.beginTransaction()
        if (!isBackedStack) {
            if (mTagList.isEmpty()) {
                mTagList.add("${mTagList.size}$targetFragment.name{y}")
            } else {
                mTagList.removeLast()
                mTagList.add("${mTagList.size}$targetFragment.name{y}")
            }
            transaction.replace(
                mHost,
                targetFragment,
                data,
                mTagList.last()
            )
        } else {
            mHidingNav?.onHide()
            mTagList.add("${mTagList.size}$targetFragment.name")
            transaction.add(
                mHost,
                targetFragment,
                data,
                mTagList.last()
            )
        }
        if (mTagList.size > 1) {
            for (i in mTagList.size - 2..0) {
                mManager.findFragmentByTag(mTagList[i])?.onPause()
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

    override fun takeBack(): Boolean {
        return if (mTagList.size > 1) {
            val transaction = mManager.beginTransaction()
            transaction.remove(mManager.findFragmentByTag(mTagList.last())!!)
            mTagList.remove(mTagList.last())
            if (mTagList.last().contains("{y}")) {
                mHidingNav?.onShow()
            }
            mManager.findFragmentByTag(mTagList.last())!!.onResume()
            transaction.commit()
            true
        } else {
            false
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