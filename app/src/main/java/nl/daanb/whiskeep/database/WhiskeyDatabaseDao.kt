package nl.daanb.whiskeep.database

import androidx.lifecycle.LiveData
import androidx.room.*
import nl.daanb.whiskeep.models.Whiskey

/**
 * The data access object that enables the app to perform queries on the local SQLite database.
 *
 * @author Daan Brocatus
 */
@Dao
interface WhiskeyDatabaseDao {
    @Insert
    suspend fun insert(whiskey: Whiskey)

    @Update
    suspend fun update(whiskey: Whiskey)

    @Delete
    suspend fun delete(whiskey: Whiskey)

    @Query("SELECT * FROM whiskey_table WHERE whiskeyId = :key")
    fun get(key: Long): LiveData<Whiskey>

    @Query("DELETE FROM whiskey_table")
    suspend fun clear()

    @Query("SELECT * FROM whiskey_table ORDER BY whiskeyId DESC")
    fun getAllWhiskeys(): LiveData<List<Whiskey>>
}