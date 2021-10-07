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
            val products = mutableListOf<DomainProduct>()
            products.add(DomainProduct.Base(
                0,
                "Баклажаны",
                50f,
                50f,
                4,
                "3 мешка"
            ))
            products.add(DomainProduct.Base(
                1,
                "Перец",
                40f,
                70f,
                3,
                "2 ящика"
            ))
            mObserver?.updatedProducts(
                products.map {
                    it.map(
                        DomainToUiProduct()
                    )
                }
            )
        }
    }

    fun onCancel() {
        mJob.cancel()
        mObserver = null
    }
}