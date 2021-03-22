package nl.daanb.whiskeep.newwhiskey

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import nl.daanb.whiskeep.database.WhiskeyDatabaseDao
import nl.daanb.whiskeep.models.Whiskey

class NewWhiskeyViewModel(val database: WhiskeyDatabaseDao) : ViewModel() {
    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun saveWhiskey(whiskey: Whiskey){
        uiScope.launch {
            withContext(Dispatchers.IO){
                database.insert(whiskey)
            }
        }
    }
}