package com.human_developing_sofr.productcalc.history.domain

import android.content.Context
import com.human_developing_sofr.productcalc.product_storage.data.ProductDBStorage
import com.human_developing_sofr.productcalc.product_storage.domain.ProductRepository

class HistoryUseCase(
    context: Context
) : ProductRepository {
    private val mDatabase = ProductDBStorage.Instance.database(context)

    override suspend fun productHistory(): List<MonthDomain> {
        val products = mDatabase.allProducts()
        // TODO Implement this method with new database
        return emptyList()
    }
}