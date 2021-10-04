package com.human_developing_sofr.productcalc.product_storage.domain

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class ProductsListVM(
    private val mObserver: ProductsObserver,
    context: Context
) : ViewModel() {
    private val mData : ProductRepository = ProductsUseCase(context)

    @DelicateCoroutinesApi
    fun fetchProducts() {
        GlobalScope.launch(Dispatchers.IO) {
            mObserver.updatedProducts(
                mData.allProducts(Date().time).map {
                    it.map(
                        DomainToUiProduct()
                    )
                }
            )
        }
    }
}