package com.human_developing_soft.productcalc.smart_fill.data

import androidx.room.Embedded
import androidx.room.Relation
import com.human_developing_soft.productcalc.product_storage.data.Product

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
data class ProductsAndName(
    @Embedded
    private val mName: ProductName,
    @Relation(
        parentColumn = "mId",
        entityColumn = "productNameId"
    )
    private val mProducts: List<Product>
) {
    fun getName() = mName

    fun getProducts() = mProducts

    fun <T> map(mapper: Mapper<T>): T {
        return mapper.map(
            mName,
            mProducts
        )
    }

    interface Mapper<T> {
        fun map(
            name: ProductName,
            products: List<Product>
        ): T
    }
}

