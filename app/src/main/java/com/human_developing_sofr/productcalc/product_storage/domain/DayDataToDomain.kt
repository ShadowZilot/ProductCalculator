package com.human_developing_sofr.productcalc.product_storage.domain

import com.human_developing_sofr.productcalc.product_storage.data.Day

class DayDataToDomain : Day.Mapper<DayDomain> {
    override fun map(id: Int, money: Int, time: Long): DayDomain {
        return DayDomain.Base(
            id,
            money
        )
    }
}