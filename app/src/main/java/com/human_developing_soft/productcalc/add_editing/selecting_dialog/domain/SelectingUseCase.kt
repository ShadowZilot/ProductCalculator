package com.human_developing_soft.productcalc.add_editing.selecting_dialog.domain

import android.content.Context
import com.human_developing_soft.productcalc.product_storage.data.DataToDomainProduct
import com.human_developing_soft.productcalc.product_storage.data.ProductDBStorage
import com.human_developing_soft.productcalc.product_storage.data.ProductToExpenditure
import com.human_developing_soft.productcalc.product_storage.domain.DomainProduct
import com.human_developing_soft.productcalc.product_storage.domain.ExpenditureDomain
import com.human_developing_soft.productcalc.product_storage.domain.ProductRepository

class SelectingUseCase(
    context: Context
) : ProductRepository {
    private val mData = ProductDBStorage.Instance.database(context)

    override suspend fun productById(id: Int): DomainProduct {
        return mData.productById(id).map(DataToDomainProduct())
    }

    override suspend fun expenditureById(id: Int): ExpenditureDomain {
        return mData.productById(id).map(ProductToExpenditure())
    }
}