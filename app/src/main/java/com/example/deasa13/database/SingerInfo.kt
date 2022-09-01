package com.example.deasa12.database.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "singers",
    indices = [
        Index("name", unique = true)
    ]
)
class SingerInfo(
    @ColumnInfo(name = "name") val name: String
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

}
