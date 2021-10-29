package com.human_developing_sofr.productcalc.add_editing.expenditures.domain

import android.content.Context
import com.human_developing_sofr.productcalc.history.domain.AllDayId
import com.human_developing_sofr.productcalc.product_storage.data.ExpenditureToProduct
import com.human_developing_sofr.productcalc.product_storage.data.ProductDBStorage
import com.human_developing_sofr.productcalc.product_storage.data.ProductToExpenditure
import com.human_developing_sofr.productcalc.product_storage.domain.*

class AExpenditureUseCase(
    private val mTime: Long,
    context: Context
) : ProductRepository {
    private val mData = ProductDBStorage.Instance.database(context)

    override suspend fun expenditureById(id: Int): ExpenditureDomain {
        return mData.productById(id).map(ProductToExpenditure())
    }

    override suspend fun insertExpenditure(expenditure: ExpenditureDomain) {
        mData.insertProduct(expenditure.map(ExpenditureToProduct(
            dayIdByTime(mTime)
        )))
    }

    override suspend fun updateExpenditure(expenditure: ExpenditureDomain) {
        mData.deleteProduct(expenditure.map(ExpenditureToProduct(
            dayIdByTime(mTime)
        )))
    }

    override suspend fun deleteExpenditure(expenditure: ExpenditureDomain) {
        mData.deleteProduct(expenditure.map(ExpenditureToProduct(
            dayIdByTime(mTime)
        )))
    }

    private suspend fun dayIdByTime(data: Long): Int {
        var allDay : AllDayDomain = AllDayDomain.Dummy()
        for (day in mData.allDays()) {
            if (day.map(SameDayDate(data))) {
                allDay = day.map(AllDayDataToDomain())
            }
        }
        return allDay.map(AllDayId())
    }
}