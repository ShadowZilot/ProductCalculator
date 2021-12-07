package com.human_developing_soft.productcalc.add_editing.products.ui

import android.os.Bundle
import android.widget.EditText
import com.human_developing_soft.productcalc.add_editing.common_ui.AddEditRestUi
import com.human_developing_soft.productcalc.product_storage.ui.ProductUi

interface AEProductUi : ProductFields {

    fun makeValid(id: Int?)

    class Base(
        private val mFields: ProductFields,
        private val mUi: AddEditRestUi
    ) : AEProductUi {
        override fun makeValid(id: Int?) {
            if (id == null) {
                mUi.goToAddingConfig()
            } else {
                mUi.goToEditConfig()
            }
        }

        override fun savedBundle(): Bundle {
            return mFields.savedBundle()
        }

        override fun product(isDeleting: Boolean): ProductUi {
            return mFields.product(isDeleting)
        }

        override fun map(
            id: Int?,
            name: String,
            weight: Float,
            priceForWeight: Float,
            priceSummary: Float,
            placeRow: String,
            note: String
        ) {
            mFields.map(
                id,
                name,
                weight,
                priceForWeight,
                priceSummary,
                placeRow,
                note
            )
        }

        override fun onShow(target: EditText) {
            mFields.onShow(target)
        }

        override fun onHide(target: EditText) {
            mFields.onHide(target)
        }
    }
}