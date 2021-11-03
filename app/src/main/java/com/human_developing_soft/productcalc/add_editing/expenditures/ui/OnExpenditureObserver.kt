package com.human_developing_soft.productcalc.add_editing.expenditures.ui

import com.human_developing_soft.productcalc.product_storage.ui.ExpenditureUi

interface OnExpenditureObserver {
    fun onExpenditureObtained(expenditure: ExpenditureUi)
}