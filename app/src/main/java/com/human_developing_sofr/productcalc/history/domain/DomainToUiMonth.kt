package com.human_developing_sofr.productcalc.history.domain

import com.human_developing_sofr.productcalc.history.ui.MonthProvider
import com.human_developing_sofr.productcalc.history.ui.MonthUi
import java.util.*

class DomainToUiMonth(
    private val mProvider: MonthProvider
) : MonthDomain.Mapper<MonthUi> {
    override fun map(time: Long, days: List<Long>): MonthUi {
        val date = GregorianCalendar.getInstance()
        date.time = Date(time)
        return MonthUi.Base(
            mProvider.monthByOrder(date.get(Calendar.MONTH)),
            days
        )
    }
}