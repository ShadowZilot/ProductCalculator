package com.human_developing_sofr.productcalc.product_storage.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "days")
data class Day(
    @PrimaryKey(autoGenerate = true)
    private val mId : Int?,
    @ColumnInfo(name = "allMoney")
    private val mAllMoney: Int,
    @ColumnInfo(name = "date")
    private val mTime: Long
) {
    fun getId() = mId

    fun getAllMoney() = mAllMoney

    fun getTime() = mTime

    fun <T> map(mapper: Mapper<T>): T {
        return mapper.map(
            mId!!,
            mAllMoney,
            mTime
        )
    }

    interface Mapper<T> {
        fun map(
            id: Int,
            money: Int,
            time: Long
        ) : T
    }
}