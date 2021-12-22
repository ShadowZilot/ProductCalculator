package com.human_developing_soft.productcalc.product_storage.domain

import android.content.Context
import android.util.Log
import com.human_developing_soft.productcalc.main.domain.ProductsInject
import com.human_developing_soft.productcalc.product_storage.data.DayDomainToData
import com.human_developing_soft.productcalc.product_storage.data.ProductDBStorage

class ProductsUseCase(
    context: Context
) : ProductRepository {
    private val mDatabase = ProductDBStorage
        .Instance.database(context)

    override suspend fun dayByDate(data: Long): AllDayDomain {
        var allDay : AllDayDomain = AllDayDomain.Dummy()
        for (day in mDatabase.allDays()) {
            if (day.map(SameDayDate(data))) {
                Log.d("AutoFill", "${day.map(ProductsInject())}")
                allDay = day.map(AllDayDataToDomain())
            }
        }
        return allDay
    }

    override suspend fun createDay(day: DayDomain, date: Long) {
        val allDays = mDatabase.allDays()
        val emptyIds = mutableListOf<Int>()
        for (dayItem in allDays) {
            if (dayItem.getProducts().isEmpty()) {
                emptyIds.add(dayItem.getDay().getId()!!)
            }
        }
        for (emptyId in emptyIds) {
            mDatabase.deleteDay(emptyId)
        }
        mDatabase.createDay(
            day.map(DayDomainToData(date))
        )
    }
}