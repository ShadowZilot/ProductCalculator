package com.human_developing_soft.productcalc.product_storage.ui

import androidx.recyclerview.widget.RecyclerView
import com.human_developing_soft.productcalc.R
import com.human_developing_soft.productcalc.databinding.ExpenditureItemBinding
import com.human_developing_soft.productcalc.product_storage.StringContext

class ExpenditureViewHolder(
    private val mBinding: ExpenditureItemBinding,
    private val mListener: OnProductClickListener
) : RecyclerView.ViewHolder(mBinding.root),
    ExpenditureUi.Mapper<Unit> {

    override fun map(id: Int?,
                     name: String, cost: Float, note: String) {
        mBinding.apply {
            val stringProvider = StringContext.Base(this.root.context)
            this.expenditureNameView.text = name
            this.expenditureNoteView.text = note
            this.expenditureCostView.text = stringProvider.string(
                R.string.formatted_summary_price, cost.toString()
            )
            this.root.setOnClickListener {
                mListener.onProductClick(id!!)
            }
        }
    }
}