package com.human_developing_sofr.productcalc.history.domain

import com.human_developing_sofr.productcalc.history.ui.MonthUi

interface OnHistoryObtained {
    fun onObtained(data: List<MonthUi>)
}