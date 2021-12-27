package com.human_developing_soft.productcalc.add_editing.products.data

import com.human_developing_soft.productcalc.product_storage.data.Product
import com.human_developing_soft.productcalc.product_storage.domain.DomainProduct

class DomainToDataProduct(
    private val mDayId : Int
) : DomainProduct.Mapper<Product> {
    override fun map(
        id: Int?,
        productNameId: Int?,
        name: String,
        weight: Float,
        priceForWeight: Float,
        placeRow: String,
        note: String
    ): Product {
        return Product(
            id,
            mDayId,
            productNameId,
            name,
            weight,
            priceForWeight,
            placeRow,
            note,
        )
    }
}