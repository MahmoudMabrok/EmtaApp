package edu.mahmoud.emta.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EmtaDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(emta: EmtaItem)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(emta: EmtaItem)

    @Query("select * from emta")
    fun listAll(): LiveData<List<EmtaItem>>

    @Query("select * from emta where id = :id")
    fun getEmtaById(id: Long): EmtaItem

    @Delete
    fun deleteEmta(it: EmtaItem)

}