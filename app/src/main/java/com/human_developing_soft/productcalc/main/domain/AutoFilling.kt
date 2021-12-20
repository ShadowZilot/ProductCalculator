package com.human_developing_soft.productcalc.main.domain

import com.human_developing_soft.productcalc.R
import com.human_developing_soft.productcalc.main.ui.ArraysProvider

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
interface AutoFilling {
    suspend fun autoFillDay(date: Long)

    suspend fun clearDatabase()

    class Base(
        private  val mRepository: AutoFillUseCase,
        private val mArrays: ArraysProvider
    ) : AutoFilling {
        private var mDayId = 0

        override suspend fun autoFillDay(date: Long) {
            val dayConstructor = DayConstructor.Base(
                mDayId,
                date,
                ExpenditureConstructor.Base(
                    mDayId,
                    mArrays.array(R.array.expenditure_list).toList()
                ),
                ProductConstructor.Base(
                    mDayId,
                    mArrays.array(R.array.product_list).toList()
                )
            )
            mRepository.injectDay(dayConstructor.constructedDay())
        }

        override suspend fun clearDatabase() {
            mRepository.deleteAllDays()
        }
    }
}