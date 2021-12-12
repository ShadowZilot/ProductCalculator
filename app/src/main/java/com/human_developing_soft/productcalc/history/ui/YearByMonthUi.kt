package com.human_developing_soft.productcalc.history.ui

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
class YearByMonthUi : MonthUi.Mapper<Int> {

    override fun map(name: String, days: List<Long>): Int {
        return days[0].years()
    }
}