package com.baranonat.yemektarifkitabi.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.baranonat.yemektarifkitabi.model.Tarif


@Database(entities = [Tarif::class], version = 1)
abstract class TarifDatabase : RoomDatabase() {
    abstract fun tarifDao(): TarifDao
}