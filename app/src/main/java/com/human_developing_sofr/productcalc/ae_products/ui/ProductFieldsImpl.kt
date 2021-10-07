package com.human_developing_sofr.productcalc.ae_products.ui

import com.human_developing_sofr.productcalc.databinding.AeFieldsBinding
import com.human_developing_sofr.productcalc.product_storage.ui.ProductUi

class ProductFieldsImpl(
    private val mBinding: AeFieldsBinding
) : ProductFields {
    override fun product(): ProductUi {
            return ProductUiFactory.Base(
                mBinding.nameInput.text.toString(),
                mBinding.weightInput.text.toString(),
                mBinding.priceInput.text.toString(),
                mBinding.placeInput.text.toString(),
                mBinding.noteInput.text.toString(),
            ).create(mBinding.root.context)
    }

    override fun map(
        id: Int?,
        name: String,
        weight: Float,
        priceForWeight: Float,
        priceSummary: Float,
        placeRow: Int,
        note: String
    ) {
        mBinding.nameInput.setText(name)
        mBinding.placeInput.setText(placeRow.toString())
        mBinding.weightInput.setText(weight.toString())
        mBinding.priceInput.setText(priceForWeight.toString())
        mBinding.noteInput.setText(note)
    }
}