package com.human_developing_sofr.productcalc.product_storage.ui

import android.content.Context
import androidx.annotation.StringRes
import com.human_developing_sofr.productcalc.add_editing.expenditures.domain.WrongExpenditureException
import com.human_developing_sofr.productcalc.product_storage.StringContext

interface ExpenditureUi {
    fun <T> map(mapper: Mapper<T>): T

    class Base(
        private val mId: Int?,
        private val mName: String,
        private val mCost: Float,
        private val mNote: String
    ) : ExpenditureUi {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(mId, mName, mCost, mNote)
        }
    }

    class Fail(
        context: Context,
        @StringRes errorMessage: Int
    ) : ExpenditureUi {
        private val mMessage = StringContext.Base(
            context
        ).string(errorMessage)

        override fun <T> map(mapper: Mapper<T>): T {
            throw WrongExpenditureException(mMessage)
        }
    }

    interface Mapper<T> {
        fun map(
            id: Int?,
            name: String,
            cost: Float,
            note: String
        ): T
    }
}