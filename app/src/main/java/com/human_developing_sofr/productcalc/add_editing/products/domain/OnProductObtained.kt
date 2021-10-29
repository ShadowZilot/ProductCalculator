package com.human_developing_sofr.productcalc.add_editing.products.domain

import com.human_developing_sofr.productcalc.product_storage.ui.ProductUi

interface OnProductObtained {
    fun onProductObtained(product: ProductUi)
}