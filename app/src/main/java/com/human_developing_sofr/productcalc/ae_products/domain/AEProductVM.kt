package com.human_developing_sofr.productcalc.ae_products.domain

import android.content.Context
import androidx.lifecycle.ViewModel
import com.human_developing_sofr.productcalc.R
import com.human_developing_sofr.productcalc.product_storage.domain.DomainToUiProduct
import com.human_developing_sofr.productcalc.product_storage.domain.ProductRepository
import com.human_developing_sofr.productcalc.product_storage.ui.ProductUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AEProductVM(
    context: Context,
    private val mListener: OnProductUpdatedListener,
    private val mProductListener: OnProductObtained
) : ViewModel() {
    private val mData: ProductRepository = AEProductUseCase(context)

    fun saveProduct(product: ProductUi) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                mData.insertProduct(
                    product.map(UiToDomainProduct())
                )
                mListener.onProductUpdated(
                    R.string.success_updating_message
                )
            } catch (e : WrongProductException) {
                mListener.onProductUpdated(
                    e.message!!
                )
            }
        }
    }

    fun deleteProduct(product: ProductUi) {
        GlobalScope.launch(Dispatchers.IO) {
            mData.deleteProduct(
                product.map(UiToDomainProduct())
            )
            mListener.onProductUpdated(
                R.string.success_deleted
            )
        }
    }

    fun updateProduct(product: ProductUi) {
        GlobalScope.launch(Dispatchers.IO) {
            mData.updateProduct(
                product.map(UiToDomainProduct())
            )
            mListener.onProductUpdated(
                R.string.success_updated
            )
        }
    }

    fun productById(id: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            val product = mData.productById(
                id
            )
            with(Dispatchers.Main) {
                mProductListener.onProductObtained(
                    product.map(DomainToUiProduct())
                )
            }
        }
    }
}