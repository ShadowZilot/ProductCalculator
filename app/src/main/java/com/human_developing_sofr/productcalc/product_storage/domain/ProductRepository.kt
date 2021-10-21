package com.human_developing_sofr.productcalc.product_storage.domain

import com.human_developing_sofr.productcalc.history.domain.MonthDomain

interface ProductRepository {
    suspend fun dayByDate(data: Long): AllDayDomain {
        return AllDayDomain.Dummy()
    }

    suspend fun productHistory(): List<MonthDomain> {return emptyList()}

    suspend fun insertProduct(product: DomainProduct) {}

    suspend fun createDay(day: DayDomain, date: Long) {}

    suspend fun productById(id: Int): DomainProduct {return DomainProduct.Dummy()}

    suspend fun updateProduct(product: DomainProduct) {}

    suspend fun deleteProduct(product: DomainProduct) {}
}