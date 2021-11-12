package com.human_developing_soft.productcalc.add_editing.products.ui

import android.os.Bundle
import com.human_developing_soft.productcalc.product_storage.ui.ProductUi

interface ProductFields: ProductUi.Mapper<Unit> {
    fun product(isDeleting: Boolean = false): ProductUi

    fun savedBundle(): Bundle
}