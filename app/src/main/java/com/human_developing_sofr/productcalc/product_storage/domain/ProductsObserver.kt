package com.human_developing_sofr.productcalc.product_storage.domain

import com.human_developing_sofr.productcalc.product_storage.ui.ProductUi

interface ProductsObserver {
    fun updatedProducts(products: List<ProductUi>, summary: Float)
}