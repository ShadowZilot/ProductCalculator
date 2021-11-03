package com.human_developing_soft.productcalc.history.domain

import com.human_developing_soft.productcalc.history.ui.MonthUi

interface OnHistoryObtained {
    fun onObtained(data: List<MonthUi>)
}