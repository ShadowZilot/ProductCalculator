package com.human_developing_soft.productcalc.add_editing.expenditures.ui

import com.human_developing_soft.productcalc.add_editing.common_ui.AddEditRestUi
import com.human_developing_soft.productcalc.product_storage.ui.ExpenditureUi

interface AExpenditureUi : ExpenditureFields {
    fun makeValid(id: Int?)

    class Base(
        private val mFields: ExpenditureFields,
        private val mRestUi: AddEditRestUi.AExpenditureUi
    ) : AExpenditureUi {
        override fun makeValid(id: Int?) {
            if (id == null) {
                mRestUi.goToAddingConfig()
            } else {
                mRestUi.goToEditConfig()
            }
        }

        override fun expenditure(): ExpenditureUi {
            return mFields.expenditure()
        }

        override fun map(id: Int?, name: String, cost: Float, note: String) {
            mFields.map(id, name, cost, note)
        }
    }
}