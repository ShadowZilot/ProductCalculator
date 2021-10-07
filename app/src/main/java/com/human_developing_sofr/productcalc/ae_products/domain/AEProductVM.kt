package com.human_developing_sofr.productcalc.ae_products.domain

import android.content.Context
import androidx.lifecycle.ViewModel
import com.human_developing_sofr.productcalc.R
import com.human_developing_sofr.productcalc.product_storage.domain.ProductRepository
import com.human_developing_sofr.productcalc.product_storage.ui.ProductUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AEProductVM(
    context: Context,
    private val mListener: OnProductUpdatedListener
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
}