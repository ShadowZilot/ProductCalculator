package com.human_developing_soft.productcalc.history.domain

class FakeDaySeparatingList(
    private val mTimes : List<Long>
) : DaySeparatingList {

    constructor(vararg times: Long): this(times.asList())

    override fun separate(): List<SeparatedDay> {
        return mTimes.map {
            SeparatedDay.Base(
                it
            )
        }
    }
}