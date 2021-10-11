package com.human_developing_sofr.productcalc.history.domain

import android.content.Context
import com.human_developing_sofr.productcalc.product_storage.data.ProductDBStorage
import com.human_developing_sofr.productcalc.history.data.SameMonthProduct
import com.human_developing_sofr.productcalc.product_storage.data.SameProductDay
import com.human_developing_sofr.productcalc.product_storage.domain.ProductRepository

class HistoryUseCase(
    context: Context
) : ProductRepository {
    private val mDatabase = ProductDBStorage.Instance.database(context)

    override suspend fun productHistory(): List<MonthDomain> {
        val products = mDatabase.allProducts()
        val result = mutableListOf<MonthDomain>()
        val tmpDay = mutableListOf<Long>()
        tmpDay.add(products[0].getTime())
        for (i in 0 until products.size-1) {
            val dayComparing = SameProductDay(products[i].getTime())
            if (!products[i+1].map(dayComparing)) {
                tmpDay.add(products[i].getTime())
                val monthComparing = SameMonthProduct(products[i])
                if (!products[i+1].map(monthComparing)) {
                    result.add(MonthDomain.Base(
                        tmpDay.toList()
                    ))
                    tmpDay.clear()
                }
            }
        }
        result.add(MonthDomain.Base(
            tmpDay.toList()
        ))
        return result
    }
}