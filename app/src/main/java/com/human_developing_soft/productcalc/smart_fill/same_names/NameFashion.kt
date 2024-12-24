package com.human_developing_soft.productcalc.smart_fill.same_names

import com.human_developing_soft.productcalc.product_storage.data.Product
import com.human_developing_soft.productcalc.smart_fill.data.ProductName

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
interface NameFashion {
    fun productName(): ProductName

    class ProductFashion(
        private val mSelecting: List<Product>
    )  {

    }
}