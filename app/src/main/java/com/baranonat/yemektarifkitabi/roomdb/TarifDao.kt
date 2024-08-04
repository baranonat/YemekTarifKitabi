package com.baranonat.yemektarifkitabi.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.baranonat.yemektarifkitabi.model.Tarif
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface TarifDao {

    @Query("Select * from tarif")
    fun getall(): Flowable<List<Tarif>>

    @Query("Select * from tarif where id=:id")
    fun findById(id:Int):Flowable<Tarif>

    @Insert
    fun insert(tarif:Tarif):Completable

    @Delete
    fun delete(tarif:Tarif):Completable
}