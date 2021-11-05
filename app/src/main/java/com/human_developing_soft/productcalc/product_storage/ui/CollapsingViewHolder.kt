package com.human_developing_soft.productcalc.product_storage.ui

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
        mBinding.root.setOnClickListener {
            mListener.onItemCollapsed(type)
        }
    }
}