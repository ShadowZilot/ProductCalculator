package com.human_developing_soft.productcalc.main.domain

import android.content.Context
import com.human_developing_soft.productcalc.product_storage.data.AllDay
import com.human_developing_soft.productcalc.product_storage.data.DayDomainToData
import com.human_developing_soft.productcalc.product_storage.data.ProductDBStorage
import com.human_developing_soft.productcalc.product_storage.domain.DayDomain
import com.human_developing_soft.productcalc.product_storage.domain.ProductRepository

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
class AutoFillUseCase(
    context: Context
) : ProductRepository {
    private val mDatabase = ProductDBStorage.Instance.database(context)

    suspend fun injectDay(allDay: AllDay) {
        mDatabase.createDay(allDay.map(DayInject()))
        val products = allDay.map(ProductsInject())
        for (product in products) {
            mDatabase.insertProduct(product)
        }
    }

    override suspend fun createDay(day: DayDomain, date: Long) {
        mDatabase.createDay(day.map(DayDomainToData(date)))
    }
}