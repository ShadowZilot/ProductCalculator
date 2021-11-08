package com.human_developing_soft.productcalc.product_storage.ui

import androidx.recyclerview.widget.DiffUtil

interface OnListDiffCalculated {
    fun onCalculated(result: DiffUtil.DiffResult)
}