package com.human_developing_soft.productcalc.smart_fill.same_names

import com.human_developing_soft.productcalc.product_storage.data.Product
import com.human_developing_soft.productcalc.product_storage.data.ProductMapper

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
class IsSimilarProduct(
    private val mOtherProduct: Product
) : ProductMapper<Boolean> {
    override fun map(
        id: Int,
        dayId: Int,
        productNameId: Int?,
        name: String,
        weight: Float,
        priceForWeight: Float,
        placeRow: String,
        note: String
    ): Boolean {
        return StringComparing.Base(
            name
        ).comparedSame(mOtherProduct.getName()) >= 0.6f
    }
}