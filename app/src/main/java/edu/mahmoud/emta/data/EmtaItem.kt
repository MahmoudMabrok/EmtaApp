package edu.mahmoud.emta.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emta")
data class EmtaItem(
    var title: String,
    var dateMillis: Long,
    var color: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
