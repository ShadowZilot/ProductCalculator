package com.human_developing_sofr.productcalc.product_storage.domain

import com.human_developing_sofr.productcalc.history.domain.MonthDomain
import com.human_developing_sofr.productcalc.product_storage.data.ExpenditureData

interface ProductRepository {
    suspend fun allProducts(data: Long): List<DomainProduct> {return emptyList()}

    suspend fun productHistory(): List<MonthDomain> {return emptyList()}

    suspend fun insertProduct(product: DomainProduct) {}

    suspend fun insertExpenditure(expenditure: ExpenditureData) {}

    suspend fun productById(id: Int): DomainProduct {return DomainProduct.Dummy()}

    suspend fun updateProduct(product: DomainProduct) {}

    suspend fun deleteProduct(product: DomainProduct) {}
}