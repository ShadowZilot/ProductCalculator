package com.human_developing_soft.productcalc.product_storage.domain

import com.human_developing_soft.productcalc.history.domain.MonthDomain

// TODO separate this to two different interfaces
interface ProductRepository {
    suspend fun dayByDate(data: Long): AllDayDomain {
        return AllDayDomain.Dummy()
    }

    suspend fun productHistory(): List<MonthDomain> {return emptyList()}

    suspend fun createDay(day: DayDomain, date: Long) {}

    suspend fun updateDay(day: DayDomain, time: Long) {}

    suspend fun dayById(id: Int): DayDomain = DayDomain.Dummy()

    suspend fun productById(id: Int): DomainProduct {return DomainProduct.Dummy()}

    suspend fun expenditureById(id: Int): ExpenditureDomain {return ExpenditureDomain.Dummy()}

    suspend fun updateProduct(product: DomainProduct) {}

    suspend fun deleteProduct(product: DomainProduct) {}

    suspend fun insertProduct(product: DomainProduct) {}

    suspend fun updateExpenditure(expenditure: ExpenditureDomain) {}

    suspend fun deleteExpenditure(expenditure: ExpenditureDomain) {}

    suspend fun insertExpenditure(expenditure: ExpenditureDomain) {}
}