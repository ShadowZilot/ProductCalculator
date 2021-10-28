package com.human_developing_sofr.productcalc.product_storage.ui

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.human_developing_sofr.productcalc.R
import com.human_developing_sofr.productcalc.databinding.CollapsingItemBinding
import com.human_developing_sofr.productcalc.product_storage.StringContext

// TODO refactor this code
interface BaseCollapsingView<T> : View.OnClickListener {
    fun fetchData(data: List<T>, summa: Int)

    fun Boolean.visibility(): Int {
        return if (this) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    class ProductCollapsing(
        private val mBinding: CollapsingItemBinding,
        private val mAdapter: BaseAdapter<ProductUi>
    ) : BaseCollapsingView<ProductUi> {
        private var mIsCollapsed = true

        init {
            mBinding.collapsingList.adapter = mAdapter as RecyclerView.Adapter<*>
            mBinding.collapsingList.addItemDecoration(
                DividerItemDecoration(
                    mBinding.root.context,
                    RecyclerView.VERTICAL
                )
            )
            mBinding.collapsingList.visibility = mIsCollapsed.visibility()
            mBinding.collapsingButton.setOnClickListener {
                onClick(it)
            }
        }

        override fun fetchData(data: List<ProductUi>, summa: Int) {
            val stringProvider = StringContext.Base(
                mBinding.root.context
            )
            mAdapter.fetchData(data)
            mBinding.sectionInfoView.text = stringProvider.string(
                R.string.collapse_title_product, summa
            )
        }

        override fun onClick(v: View?) {
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

    class ExpenditureCollapsing(
        private val mBinding: CollapsingItemBinding,
        private val mAdapter: BaseAdapter<ExpenditureUi>
    ) : BaseCollapsingView<ExpenditureUi> {
        private var mIsCollapsed = true

        init {
            mBinding.collapsingList.adapter = mAdapter as RecyclerView.Adapter<*>
            mBinding.collapsingList.addItemDecoration(
                DividerItemDecoration(
                    mBinding.root.context,
                    RecyclerView.HORIZONTAL
                )
            )
            mBinding.collapsingList.visibility = mIsCollapsed.visibility()
            mBinding.collapsingButton.setOnClickListener {
                onClick(it)
            }
        }

        override fun fetchData(data: List<ExpenditureUi>, summa: Int) {
            val stringProvider = StringContext.Base(
                mBinding.root.context
            )
            mAdapter.fetchData(data)
            mBinding.sectionInfoView.text = stringProvider.string(
                R.string.collapse_title_expenditure, summa
            )
        }

        override fun onClick(v: View?) {
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
}