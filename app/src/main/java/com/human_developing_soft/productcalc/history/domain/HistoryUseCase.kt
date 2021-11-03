package com.human_developing_soft.productcalc.history.domain

import android.content.Context
import com.human_developing_soft.productcalc.product_storage.data.ProductDBStorage
import com.human_developing_soft.productcalc.product_storage.domain.ProductRepository

class HistoryUseCase(
    context: Context
) : ProductRepository {
    private val mDatabase = ProductDBStorage.Instance.database(context)

    override suspend fun productHistory(): List<MonthDomain> {
        return MonthSeparatingList.Base(
            mDatabase.allDays()
        ).separate()
    }
}