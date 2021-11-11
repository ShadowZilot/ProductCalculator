package com.human_developing_soft.productcalc.add_editing.products.ui

import com.human_developing_soft.productcalc.databinding.AeFieldsBinding
import com.human_developing_soft.productcalc.product_storage.ui.ProductUi

class ProductFieldsImpl(
    private val mBinding: AeFieldsBinding,
    private val mId: Int? = null
) : ProductFields {
    override fun product(isDeleting: Boolean): ProductUi {
            return ProductUiFactory.Base(
                mId,
                mBinding.nameInput.text.toString(),
                mBinding.weightInput.text.toString(),
                mBinding.priceInput.text.toString(),
                mBinding.placeInput.text.toString(),
                mBinding.noteInput.text.toString(),
            ).create(mBinding.root.context, isDeleting)
    }

    override fun map(
        id: Int?,
        name: String,
        weight: Float,
        priceForWeight: Float,
        priceSummary: Float,
        placeRow: String,
        note: String
    ) {
        mBinding.nameInput.setText(name)
        mBinding.placeInput.setText(placeRow)
        mBinding.weightInput.setText(weight.toString())
        mBinding.priceInput.setText(priceForWeight.toString())
        mBinding.noteInput.setText(note)
    }
}