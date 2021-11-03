package com.human_developing_soft.productcalc.product_storage.ui

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View
import androidx.annotation.StringRes
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.human_developing_soft.productcalc.databinding.CollapsingItemBinding
import com.human_developing_soft.productcalc.product_storage.StringContext

abstract class BaseCollapsingView<T>(
    protected val mBinding: CollapsingItemBinding,
    private val mAdapter: BaseAdapter<T>,
    @StringRes protected val mMessage: Int
) : View.OnClickListener {
    protected var mIsCollapsed = true

    init {
        mBinding.collapsingList.adapter = mAdapter as RecyclerView.Adapter<*>
        mBinding.collapsingList.layoutManager = NoScrollingLinearManager(
            mBinding.root.context
        )
        mBinding.collapsingList.addItemDecoration(
            DividerItemDecoration(
                mBinding.collapsingList.context,
                RecyclerView.VERTICAL
            )
        )
        mBinding.collapsingList.visibility = mIsCollapsed.visibility()
        mBinding.collapsingButton.setOnClickListener {
            onClick(it)
        }
    }

    fun fetchData(data: List<T>, summa: Int) {
        mAdapter.fetchData(data)
        val stringProvider = StringContext.Base(
            mBinding.root.context
        )
        mBinding.sectionInfoView.text = stringProvider.string(
            mMessage, summa
        )
    }

    fun Boolean.visibility(): Int {
        return if (this) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    override fun onClick(view: View) {
        mIsCollapsed = !mIsCollapsed
        mBinding.collapsingButton.apply {
            val animator = ObjectAnimator.ofFloat(
                mBinding.collapsingButton,
                "rotation", this.rotation, this.rotation + 180
            )
            animator.duration = 100
            animator.start()
        }
        mBinding.collapsingList.apply {
            val animator: ObjectAnimator = if (mIsCollapsed) {
                ObjectAnimator.ofFloat(
                    this, "alpha", 1f, 0f
                )
            } else {
                ObjectAnimator.ofFloat(
                    this, "alpha", 0f, 1f
                )
            }
            animator.duration = 100
            animator.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) {}

                override fun onAnimationEnd(animation: Animator?) {
                    this@apply.visibility = mIsCollapsed.visibility()
                }

                override fun onAnimationCancel(animation: Animator?) {}

                override fun onAnimationRepeat(animation: Animator?) {}
            })
            animator.start()
        }
        mBinding.sectionInfoView.apply {
            val animator: ObjectAnimator = if (mIsCollapsed) {
                ObjectAnimator.ofFloat(
                    this, "elevation", 8f, 0f
                )
            } else {
                ObjectAnimator.ofFloat(
                    this, "elevation", 0f, 8f
                )
            }
            animator.duration = 100
            animator.start()
        }
    }
}