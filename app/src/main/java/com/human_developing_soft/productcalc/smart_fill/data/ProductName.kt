package com.human_developing_soft.productcalc.smart_fill.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
@Entity(tableName = "names")
data class ProductName(
    @PrimaryKey(autoGenerate = true)
    private val mId: Int?,
    @ColumnInfo(name = "productName")
    private val mName: String
) {
    fun getId() = mId

    fun getName() = mName

    interface Mapper<T> {
        fun map(id: Int, name: String): T
    }
}