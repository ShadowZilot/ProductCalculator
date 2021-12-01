package com.human_developing_soft.productcalc.add_editing.products.ui

import android.os.Bundle
import android.view.View
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
                mBinding.sumPriceInput.text.toString(),
                mBinding.noteInput.text.toString(),
            ).create(mBinding.root.context, isDeleting)
    }

    override fun savedBundle(): Bundle {
        val savedState = Bundle()
        mBinding.let {
            savedState.putInt("id", mId ?: -1)
            savedState.putString("name", it.nameInput.text.toString())
            savedState.putString("weight", it.weightInput.text.toString())
            savedState.putString("price", it.priceInput.text.toString())
            savedState.putString("place", it.placeInput.text.toString())
            savedState.putString("sumPrice", it.priceInput.text.toString())
            savedState.putString("note", it.noteInput.text.toString())
        }
        return savedState
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
        mBinding.sumPriceInputContainer.visibility = View.VISIBLE
        mBinding.sumPriceInput.setText(priceSummary.toString())
        mBinding.noteInput.setText(note)
    }
}