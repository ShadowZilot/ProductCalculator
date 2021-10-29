package com.human_developing_sofr.productcalc.add_editing.expenditures.ui

import com.human_developing_sofr.productcalc.product_storage.ui.ExpenditureUi

interface ExpenditureFields : ExpenditureUi.Mapper<Unit> {
    fun expenditure(): ExpenditureUi
}