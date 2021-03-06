package com.human_developing_soft.productcalc.product_storage.ui

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.human_developing_soft.productcalc.R
import com.human_developing_soft.productcalc.databinding.ProductItemBinding
import com.human_developing_soft.productcalc.product_storage.StringContext

class ProductViewHolder(
    private val mBinding: ProductItemBinding,
    private val mListener: OnProductClickListener
) : RecyclerView.ViewHolder(mBinding.root), ProductUi.Mapper<Unit> {

    @SuppressLint("SetTextI18n")
    override fun map(
        id: Int?,
        name: String,
        weight: Float,
        priceForWeight: Float,
        priceSummary: Float,
        placeRow: String,
        note: String
    ) {
        val stringProvider = StringContext.Base(mBinding.root.context)
        mBinding.root.setOnClickListener {
            mListener.onProductClick(id!!)
        }
        mBinding.productNameView.text = name
        mBinding.productWeightView.text = stringProvider.string(R.string.formatted_weight,
            weight) + " ${stringProvider.string(R.string.weight_emoji)}"
        mBinding.productPriceView.text = stringProvider.string(R.string.formatted_price,
            priceForWeight) + " ${stringProvider.string(R.string.money_emoji)}"
        mBinding.summaryPriceView.text = stringProvider.string(R.string.formatted_summary_price,
            priceSummary) + " ${stringProvider.string(R.string.money_emoji)}"
        mBinding.productRowView.text = if (placeRow == "") "" else
            stringProvider.string(R.string.formatted_place_row,
            placeRow)
        mBinding.productNoteView.text = note
    }
}