package com.human_developing_sofr.productcalc.product_storage.ui

import android.content.Context
import androidx.annotation.StringRes
import com.human_developing_sofr.productcalc.ae_products.domain.WrongProductException
import com.human_developing_sofr.productcalc.product_storage.StringContext

interface ProductUi {
    fun <T> map(mapper: Mapper<T>): T

    class Base(
        private val mId: Int?,
        private val mName: String,
        private val mWeight: Float,
        private val mPriceForWeight: Float,
        private val mPriceSummary: Float,
        private val mPlaceRow: String,
        private val mNote: String
    ) : ProductUi {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(
                mId,
                mName,
                mWeight,
                mPriceForWeight,
                mPriceSummary,
                mPlaceRow,
                mNote
            )
        }
    }

    class Fail(
        @StringRes message: Int,
        context: Context
    ) : ProductUi {
        private val mMessage = StringContext.Base(context).string(message)

        override fun <T> map(mapper: Mapper<T>): T {
            throw WrongProductException(mMessage)
        }
    }


    interface Mapper<T> {
        fun map(
            id: Int?,
            name: String,
            weight: Float,
            priceForWeight: Float,
            priceSummary: Float,
            placeRow: String,
            note: String
        ): T
    }
}