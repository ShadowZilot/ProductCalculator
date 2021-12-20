package com.human_developing_soft.productcalc.main.domain

import com.human_developing_soft.productcalc.product_storage.data.AllDay
import com.human_developing_soft.productcalc.product_storage.data.Day
import com.human_developing_soft.productcalc.product_storage.data.Product

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
class ProductsInject : AllDay.Mapper<List<Product>> {
    override fun map(day: Day, products: List<Product>): List<Product> {
        return products
    }
}