package com.human_developing_sofr.productcalc.edit_day_money.domain

import android.content.Context
import com.human_developing_sofr.productcalc.history.domain.DayTiming
import com.human_developing_sofr.productcalc.product_storage.data.DayDomainToData
import com.human_developing_sofr.productcalc.product_storage.data.ProductDBStorage
import com.human_developing_sofr.productcalc.product_storage.data.ProductStorage
import com.human_developing_sofr.productcalc.product_storage.domain.DayDataToDomain
import com.human_developing_sofr.productcalc.product_storage.domain.DayDomain
import com.human_developing_sofr.productcalc.product_storage.domain.ProductRepository

class EditMoneyUseCase(
    context: Context
) : ProductRepository {
    private val mDatabase: ProductStorage = ProductDBStorage
        .Instance.database(context)

    override suspend fun dayById(id: Int): DayDomain {
        return mDatabase.dayById(id).map(
            DayDataToDomain()
        )
    }

    override suspend fun updateDay(day: DayDomain, time: Long) {
        mDatabase.updateDay(
            day.map(
                DayDomainToData(time)
            )
        )
    }
}