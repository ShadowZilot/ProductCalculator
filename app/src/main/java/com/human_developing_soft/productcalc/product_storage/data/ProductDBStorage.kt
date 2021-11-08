package com.human_developing_soft.productcalc.product_storage.data

import android.content.Context
import androidx.room.Room
import com.human_developing_soft.productcalc.history.ui.byDay
import java.util.*

class ProductDBStorage private constructor(
    context: Context
): ProductStorage {
    private val mDatabase: ProductDatabase = Room.databaseBuilder(
        context.applicationContext,
        ProductDatabase::class.java,
        "Products.db"
    ).build()

    override suspend fun allDays(): List<AllDay> {
        val all = mDatabase.productDao().allDays().toMutableList()
        for (i in all.indices) {
            if (all[i].getProducts().isEmpty()
                && all[i].getDay().getTime().byDay() != Date().time.byDay()) {
                all.remove(all[i])
            }
        }
        return all.toList()
    }

    override suspend fun insertProduct(product: Product) {
        mDatabase
            .productDao()
            .insertProduct(product)
    }

    override suspend fun updateProduct(product: Product) {
        mDatabase.productDao().updateProduct(product)
    }

    override suspend fun createDay(day: Day) {
        mDatabase.productDao().createDay(day)
    }

    override suspend fun dayById(id: Int) = mDatabase.productDao().dayById(id)

    override suspend fun updateDay(day: Day) {
        mDatabase.productDao().updateDay(day)
    }

    override suspend fun deleteProduct(product: Product) {
        mDatabase.productDao().deleteProduct(product)
    }

    override suspend fun productById(id: Int): Product {
        return mDatabase.productDao().productById(id)
    }

    object Instance {
        private var mInstance: ProductStorage? = null

        fun database(context: Context): ProductStorage {
            if (mInstance == null) {
                mInstance = ProductDBStorage(context)
            }
            return mInstance!!
        }
    }
}