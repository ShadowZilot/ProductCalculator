package com.human_developing_soft.productcalc.add_editing.products.ui

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

        override fun product(): ProductUi {
            return mFields.product()
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
    }
}