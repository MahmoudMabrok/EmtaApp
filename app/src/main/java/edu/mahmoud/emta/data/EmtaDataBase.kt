package edu.mahmoud.emta.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [EmtaItem::class],
    version = 1,
    exportSchema = false
)
abstract class EmtaDataBase : RoomDatabase() {

    abstract fun emtaDto(): EmtaDAO

    companion object {

        fun createDb(context: Context): EmtaDataBase {
            return Room.databaseBuilder(context, EmtaDataBase::class.java, "emta")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}