package nl.daanb.whiskeep.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "whiskey_table")
data class Whiskey (

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "type")
    var type: String,

    @ColumnInfo(name = "abv")
    var abv: Float,

    @ColumnInfo(name = "price")
    var price: Float,

    @ColumnInfo(name = "notes")
    var notes: String,

    @ColumnInfo(name = "rating")
    var rating: Int,

    @ColumnInfo(name = "image")
    var image: String,

    @PrimaryKey(autoGenerate = true)
    var whiskeyId: Long = 0L
)