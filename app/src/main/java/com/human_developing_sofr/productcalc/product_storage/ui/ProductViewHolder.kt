package com.human_developing_sofr.productcalc.product_storage.ui

import androidx.recyclerview.widget.RecyclerView
import com.human_developing_sofr.productcalc.R
import com.human_developing_sofr.productcalc.databinding.ProductItemBinding
import com.human_developing_sofr.productcalc.product_storage.StringContext

class ProductViewHolder(
    private val mBinding: ProductItemBinding,
    private val mListener: OnProductClickListener
) : RecyclerView.ViewHolder(mBinding.root), ProductUi.Mapper<Unit> {

    override fun map(
        id: Int?,
        name: String,
        weight: Float,
        priceForWeight: Float,
        priceSummary: Float,
        placeRow: Int,
        note: String
    ) {
        val stringProvider = StringContext.Base(mBinding.root.context)
        mBinding.root.setOnClickListener {
            mListener.onProductClick(id!!)
        }
        mBinding.productNameView.text = name
        mBinding.productWeightView.text = stringProvider.string(R.string.formatted_weight,
            weight.toString())
        mBinding.productPriceView.text = stringProvider.string(R.string.formatted_price,
            priceForWeight.toString())
        mBinding.summaryPriceView.text = stringProvider.string(R.string.formatted_summary_price,
            priceSummary.toString())
        mBinding.productRowView.text = stringProvider.string(R.string.formatted_place_row,
            placeRow.toString())
        mBinding.productNoteView.text = note
    }
}