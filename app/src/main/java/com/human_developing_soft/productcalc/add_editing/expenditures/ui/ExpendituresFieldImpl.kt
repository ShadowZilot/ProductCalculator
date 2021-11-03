package com.human_developing_soft.productcalc.add_editing.expenditures.ui

import com.human_developing_soft.productcalc.databinding.AeExpenditureFieldsBinding
import com.human_developing_soft.productcalc.product_storage.ui.ExpenditureUi

class ExpendituresFieldImpl(
    private val mBinding: AeExpenditureFieldsBinding,
    private val mId: Int?
) : ExpenditureFields {
    override fun expenditure(): ExpenditureUi {
        return ExpenditureUiFactory.Base(
            mId,
            mBinding.nameInput.text.toString(),
            mBinding.priceInput.text.toString(),
            mBinding.noteInput.text.toString()
        ).create(mBinding.root.context)
    }

    override fun map(id: Int?, name: String, cost: Float, note: String) {
        mBinding.apply {
            this.nameInput.setText(name)
            this.priceInput.setText(cost.toString())
            this.noteInput.setText(note)
        }
    }
}