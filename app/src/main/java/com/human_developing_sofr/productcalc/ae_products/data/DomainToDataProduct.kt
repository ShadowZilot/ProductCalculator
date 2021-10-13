package com.human_developing_sofr.productcalc.ae_products.data

import com.human_developing_sofr.productcalc.product_storage.data.Product
import com.human_developing_sofr.productcalc.product_storage.domain.DomainProduct
import java.util.*

class DomainToDataProduct(
    private val mTime : Long = Date().time
) : DomainProduct.Mapper<Product> {
    override fun map(
        id: Int?,
        name: String,
        weight: Float,
        priceForWeight: Float,
        placeRow: Int,
        note: String
    ): Product {
        return Product(
            id,
            name,
            weight,
            priceForWeight,
            placeRow,
            note,
            mTime
        )
    }
}