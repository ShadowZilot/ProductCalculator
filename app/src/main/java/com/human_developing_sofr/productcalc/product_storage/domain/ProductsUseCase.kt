package com.human_developing_sofr.productcalc.product_storage.domain

import android.content.Context
import com.human_developing_sofr.productcalc.product_storage.data.DataToDomainProduct
import com.human_developing_sofr.productcalc.product_storage.data.Product
import com.human_developing_sofr.productcalc.product_storage.data.ProductDBStorage

class ProductsUseCase(
    context: Context
) : ProductRepository {
    private val mDatabase = ProductDBStorage
        .Instance.database(context)

    override suspend fun dayByDate(data: Long): AllDayDomain {
        var allDay : AllDayDomain = AllDayDomain.Dummy()
        for (day in mDatabase.allDays()) {
            if (day.map(SameDayDate(data))) {
                allDay = day.map(AllDayDataToDomain())
            }
        }
        return allDay
    }
}