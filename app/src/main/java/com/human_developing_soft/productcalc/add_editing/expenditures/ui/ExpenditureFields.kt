package com.human_developing_soft.productcalc.add_editing.expenditures.ui

import com.human_developing_soft.productcalc.product_storage.ui.ExpenditureUi

interface ExpenditureFields : ExpenditureUi.Mapper<Unit> {
    fun expenditure(isDeleting: Boolean = false): ExpenditureUi
}