package com.human_developing_sofr.productcalc.product_storage.domain

import com.human_developing_sofr.productcalc.product_storage.ui.AllDayUi

interface ProductsObserver {
    fun updatedProducts(day: AllDayUi)
}