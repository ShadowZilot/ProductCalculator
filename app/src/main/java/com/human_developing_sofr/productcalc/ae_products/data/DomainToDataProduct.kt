package com.human_developing_sofr.productcalc.ae_products.data

import com.human_developing_sofr.productcalc.product_storage.data.Product
import com.human_developing_sofr.productcalc.product_storage.domain.DomainProduct
import java.util.*

class DomainToDataProduct(
    private val mDayId : Int
) : DomainProduct.Mapper<Product> {
    override fun map(
        id: Int?,
        name: String,
        weight: Float,
        priceForWeight: Float,
        placeRow: String,
        note: String
    ): Product {
        return Product(
            id,
            mDayId,
            name,
            weight,
            priceForWeight,
            placeRow,
            note,
        )
    }
}