package nl.daanb.whiskeep.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * The model used throughout this application. The model keeps track of most of the information in the application.
 *
 * @author Daan Brocatus
 *
 * @param name The whiskey's name
 * @param type The whiskey's type
 * @param abv The whiskey's alcohol percentage
 * @param price The whiskey's price in euros
 * @param notes The user's notes on the whiskey
 * @param rating The user's rating on the whiskey out of 100
 * @param image The whiskey's thumbnail image url
 * @param area The whiskey's area of origin
 * @param whiskeyId The whiskey's unique identifier. These are auto-generated.
 */
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

    @ColumnInfo(name = "Area")
    var area: String = "",

    @PrimaryKey(autoGenerate = true)
    var whiskeyId: Long = 0L
)