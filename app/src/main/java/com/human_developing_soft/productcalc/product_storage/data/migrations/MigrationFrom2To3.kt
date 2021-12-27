package com.human_developing_soft.productcalc.product_storage.data.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
class MigrationFrom2To3 : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        if (database.needUpgrade(3)) {
            database.execSQL(
                "CREATE TABLE `names` ( `mId` INTEGER," +
                        " `productName` TEXT NOT NULL, " +
                        "PRIMARY KEY(`mId`))"
            )
            database.execSQL("ALTER TABLE products ADD COLUMN productNameId INTEGER")
        }
    }
}