package com.human_developing_sofr.productcalc.ae_products.domain

import com.human_developing_sofr.productcalc.product_storage.ui.ProductUi

interface OnProductObtained {
    fun onProductObtained(product: ProductUi)
}