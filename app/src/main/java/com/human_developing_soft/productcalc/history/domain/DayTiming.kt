package com.human_developing_soft.productcalc.history.domain

class DayTiming : SeparatedDay.Mapper<Long> {
    override fun map(commonTime: Long): Long {
        return commonTime
    }
}