package com.human_developing_sofr.productcalc.product_storage.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    private val mId: Int?,
    @ColumnInfo(name = "dayId")
    private val mDayId: Int,
    @ColumnInfo(name = "name")
    private val mName: String,
    @ColumnInfo(name = "weight")
    private val mWeight: Float,
    @ColumnInfo(name = "priceForWeight")
    private val mPriceForWeight: Float,
    @ColumnInfo(name = "placeRow")
    private val mPlaceRow: String,
    @ColumnInfo(name = "note")
    private val mNote: String,
) {
    fun <T> map(mapper: ProductMapper<T>): T {
        return mapper.map(
            mId!!,
            mDayId,
            mName,
            mWeight,
            mPriceForWeight,
            mPlaceRow,
            mNote
        )
    }

    fun getId() = mId

    fun getDayId() = mDayId

    fun getName() = mName

    fun getWeight() = mWeight

    fun getPriceForWeight() = mPriceForWeight

    fun getPlaceRow() = mPlaceRow

    fun getNote() = mNote
}
