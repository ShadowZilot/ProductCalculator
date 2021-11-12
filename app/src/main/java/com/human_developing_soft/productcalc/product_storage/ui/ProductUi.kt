package com.human_developing_soft.productcalc.product_storage.ui

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import com.human_developing_soft.productcalc.add_editing.products.domain.WrongProductException
import com.human_developing_soft.productcalc.product_storage.StringContext

interface ProductUi {
    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        private val mId: Int?,
        private val mName: String,
        private val mWeight: Float,
        private val mPriceForWeight: Float,
        private val mPriceSummary: Float,
        private val mPlaceRow: String,
        private val mNote: String
    ) : ProductUi {
        constructor(savedProduct: Bundle) : this(
            if (savedProduct.getInt("id") == -1) null
            else savedProduct.getInt("id"),
            savedProduct.getString("name")!!,
            if (savedProduct.getString("weight")!!.isEmpty()) 0f
            else savedProduct.getString("weight")!!.toFloatSafety(),
            if (savedProduct.getString("price")!!.isEmpty()) 0f
            else savedProduct.getString("price")!!.toFloatSafety(),
                    savedProduct.getString("weight")!!.toFloatSafety() *
                    savedProduct.getString("price")!!.toFloatSafety(),
                    savedProduct.getString("place")!!,
                    savedProduct.getString("note")!!
        )

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

fun String.toFloatSafety(): Float {
    return if (this.isEmpty()) 0f else this.toFloat()
}