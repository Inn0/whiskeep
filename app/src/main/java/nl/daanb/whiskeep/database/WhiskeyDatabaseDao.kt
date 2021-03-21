package nl.daanb.whiskeep.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import nl.daanb.whiskeep.models.Whiskey

@Dao
interface WhiskeyDatabaseDao {
    @Insert
    fun instert(whiskey: Whiskey)

    @Update
    fun update(whiskey: Whiskey)

    @Query("SELECT * FROM whiskey_table WHERE whiskeyId = :key")
    fun get(key: Long): Whiskey

    @Query("DELETE FROM whiskey_table")
    fun clear()

    @Query("SELECT * FROM whiskey_table ORDER BY whiskeyId DESC")
    fun getAllWhiskeys(): LiveData<List<Whiskey>>
}