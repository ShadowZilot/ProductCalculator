package com.human_developing_soft.productcalc.add_editing.products.domain

import com.human_developing_soft.productcalc.product_storage.ui.ProductUi

interface OnProductObtained {
    fun onProductObtained(product: ProductUi)
}