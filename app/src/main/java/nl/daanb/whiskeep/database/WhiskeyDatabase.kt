package nl.daanb.whiskeep.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import nl.daanb.whiskeep.models.Whiskey

/**
 * Ensures that there is one unique instance of the database available. Whenever the version changes (i.e. the model the database is supposed to store changes) it destroys the old database and creates a new one that is capable of holding objects of the new model class.
 *
 * @author Daan Brocatus
 */
@Database(entities = [Whiskey::class], version = 4, exportSchema = false)
abstract class WhiskeyDatabase: RoomDatabase() {
    abstract val whiskeyDatabaseDao: WhiskeyDatabaseDao

    companion object{
        @Volatile
        private var INSTANCE: WhiskeyDatabase? = null

        fun getInstance(context: Context): WhiskeyDatabase{
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(context.applicationContext, WhiskeyDatabase::class.java, "whiskey_database").fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}