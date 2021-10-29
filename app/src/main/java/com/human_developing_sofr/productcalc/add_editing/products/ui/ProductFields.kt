package com.human_developing_sofr.productcalc.add_editing.products.ui

import com.human_developing_sofr.productcalc.product_storage.ui.ProductUi

interface ProductFields: ProductUi.Mapper<Unit> {
    fun product(): ProductUi
}