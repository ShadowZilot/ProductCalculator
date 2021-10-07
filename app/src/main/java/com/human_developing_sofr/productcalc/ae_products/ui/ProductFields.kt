package com.human_developing_sofr.productcalc.ae_products.ui

import com.human_developing_sofr.productcalc.product_storage.ui.ProductUi

interface ProductFields: ProductUi.Mapper<Unit> {
    fun product(): ProductUi
}