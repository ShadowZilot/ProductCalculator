package com.human_developing_sofr.productcalc.product_storage.domain

import android.content.Context
import androidx.lifecycle.ViewModel
import com.human_developing_sofr.productcalc.product_storage.ui.ProductUi
import kotlinx.coroutines.*
import java.util.*

class ProductsListVM(
    private var mObserver: ProductsObserver?,
    context: Context
) : ViewModel() {
    private val mData : ProductRepository = ProductsUseCase(context)
    private lateinit var mJob : Job

    @DelicateCoroutinesApi
    fun fetchProducts() {
        mJob = GlobalScope.launch(Dispatchers.IO) {
            mObserver?.updatedProducts(
                mData.allProducts(Date().time).map {
                    it.map(DomainToUiProduct())
                },
                mData.allProducts(Date().time).summa()
            )
        }
    }

    fun onCancel() {
        mJob.cancel()
        mObserver = null
    }
}

fun List<DomainProduct>.summa(): Float {
    var summa = 0f
    forEach {
        summa += it.map(
            DomainSumma()
        )
    }
    return summa
}