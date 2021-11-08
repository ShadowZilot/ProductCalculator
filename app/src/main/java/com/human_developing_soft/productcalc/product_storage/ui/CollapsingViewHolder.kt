package com.human_developing_soft.productcalc.product_storage.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.recyclerview.widget.RecyclerView
import com.human_developing_soft.productcalc.R
import com.human_developing_soft.productcalc.databinding.CollapsingItemBinding
import com.human_developing_soft.productcalc.product_storage.StringContext

class CollapsingViewHolder(
    private val mBinding: CollapsingItemBinding,
    private val mListener: CollapsingListener
) : RecyclerView.ViewHolder(mBinding.root),
    CollapsingItemUi.Mapper<Unit> {
    override fun map(type: Int, isCollapsed: Boolean, summa: Int) {
        val string = StringContext.Base(mBinding.root.context)
        val res = if (type == 0)
            R.string.collapse_title_product else R.string.collapse_title_expenditure
        mBinding.sectionInfoView.text = string.string(res, summa.toString())
        mBinding.collapsingButton.rotation = if (isCollapsed) 180f else 0f
        mBinding.root.elevation = if (isCollapsed) 0f else 10f
        mBinding.root.setOnClickListener {
            val buttonAnim = ObjectAnimator.ofFloat(
                mBinding.collapsingButton,
                "rotation",
                mBinding.collapsingButton.rotation,
                mBinding.collapsingButton.rotation+180f
            )
            val shadowAnim = ObjectAnimator.ofFloat(
                mBinding.root,
                "elevation",
                if (isCollapsed) 0f else 10f,
                if (isCollapsed) 10f else 0f
            )
            val set = AnimatorSet()
            set.duration = 100
            set.playTogether(buttonAnim, shadowAnim)
            set.start()
            mListener.onItemCollapsed(type)
        }
    }
}