package com.human_developing_sofr.productcalc.ae_products.domain

import android.content.Context
import com.human_developing_sofr.productcalc.ae_products.data.DomainToDataProduct
import com.human_developing_sofr.productcalc.history.domain.AllDayId
import com.human_developing_sofr.productcalc.product_storage.data.DataToDomainProduct
import com.human_developing_sofr.productcalc.product_storage.data.ProductDBStorage
import com.human_developing_sofr.productcalc.product_storage.domain.*

class AEProductUseCase(
    context: Context,
    private val mTime : Long
) : ProductRepository {
    private val mDatabase = ProductDBStorage
        .Instance.database(context)

    override suspend fun insertProduct(product: DomainProduct) {
        mDatabase.insertProduct(
            product.map(DomainToDataProduct(dayIdByTime(mTime)))
        )
    }

    override suspend fun updateProduct(product: DomainProduct) {
        mDatabase.updateProduct(
            product.map(DomainToDataProduct(dayIdByTime(mTime)))
        )
    }

    override suspend fun deleteProduct(product: DomainProduct) {
        mDatabase.deleteProduct(
            product.map(DomainToDataProduct(dayIdByTime(mTime)))
        )
    }

    override suspend fun productById(id: Int): DomainProduct {
        return mDatabase.productById(id).map(
            DataToDomainProduct()
        )
    }

    private suspend fun dayIdByTime(data: Long): Int {
        var allDay : AllDayDomain = AllDayDomain.Dummy()
        for (day in mDatabase.allDays()) {
            if (day.map(SameDayDate(data))) {
                allDay = day.map(AllDayDataToDomain())
            }
        }
        return allDay.map(AllDayId())
    }
}