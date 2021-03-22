package nl.daanb.whiskeep.database

import androidx.lifecycle.LiveData
import androidx.room.*
import nl.daanb.whiskeep.models.Whiskey

@Dao
interface WhiskeyDatabaseDao {
    @Insert
    fun insert(whiskey: Whiskey)

    @Update
    fun update(whiskey: Whiskey)

    @Delete
    fun delete(whiskey: Whiskey)

    @Query("SELECT * FROM whiskey_table WHERE whiskeyId = :key")
    fun get(key: Long): Whiskey

    @Query("DELETE FROM whiskey_table")
    fun clear()

    @Query("SELECT * FROM whiskey_table ORDER BY whiskeyId DESC")
    fun getAllWhiskeys(): LiveData<List<Whiskey>>
}