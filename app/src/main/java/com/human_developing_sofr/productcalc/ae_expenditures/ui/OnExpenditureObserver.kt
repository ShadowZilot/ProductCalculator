package com.human_developing_sofr.productcalc.ae_expenditures.ui

import com.human_developing_sofr.productcalc.product_storage.ui.ExpenditureUi

interface OnExpenditureObserver {
    fun onExpenditureObtained(expenditure: ExpenditureUi)
}