package com.human_developing_soft.productcalc.add_editing.products.domain

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.human_developing_soft.productcalc.R
import com.human_developing_soft.productcalc.product_storage.domain.DomainToUiProduct
import com.human_developing_soft.productcalc.product_storage.domain.ProductRepository
import com.human_developing_soft.productcalc.product_storage.ui.ProductUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AEProductVM(
    context: Context,
    private var mListener: OnProductUpdatedListener,
    private var mProductListener: OnProductObtained,
    time : Long
) : ViewModel() {
    private val mData: ProductRepository = AEProductUseCase(context,
        time)

    fun saveProduct(product: ProductUi) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mData.insertProduct(
                    product.map(UiToDomainProduct())
                )
                withContext(Dispatchers.Main) {
                    mListener.onProductUpdated(
                        R.string.success_updating_message
                    )
                }
            } catch (e : WrongProductException) {
                withContext(Dispatchers.Main) {
                    mListener.onProductUpdated(
                        e.message!!
                    )
                }
            }
        }
    }

    fun deleteProduct(product: ProductUi) {
        viewModelScope.launch(Dispatchers.IO) {
            mData.deleteProduct(
                product.map(UiToDomainProduct())
            )
            withContext(Dispatchers.Main) {
                mListener.onProductUpdated(
                    R.string.success_deleted
                )
            }
        }
    }

    fun updateProduct(product: ProductUi) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mData.updateProduct(
                    product.map(UiToDomainProduct())
                )
                withContext(Dispatchers.Main) {
                    mListener.onProductUpdated(R.string.success_updated)
                }
            } catch (e : WrongProductException) {
                withContext(Dispatchers.Main) {
                    mListener.onProductUpdated(
                        e.message!!
                    )
                }
            }
        }
    }

    fun productById(id: Int?) {
        if (id != null) {
            viewModelScope.launch(Dispatchers.IO) {
                val product = mData.productById(
                    id
                )
                withContext(Dispatchers.Main) {
                    mProductListener.onProductObtained(
                        product.map(DomainToUiProduct())
                    )
                }
            }
        }
    }

    fun redefineReferences(
        observer: OnProductObtained,
        listener: OnProductUpdatedListener
    ) {
        mProductListener = observer
        mListener = listener
    }
}