package nl.daanb.whiskeep.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import nl.daanb.whiskeep.database.WhiskeyDatabaseDao
import nl.daanb.whiskeep.models.Whiskey

class HomeViewModel(private val database: WhiskeyDatabaseDao): ViewModel() {
    var whiskeys : LiveData<List<Whiskey>> = database.getAllWhiskeys()

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun deleteWhiskey(whiskey: Whiskey){
        uiScope.launch {
            withContext(Dispatchers.IO){
                database.delete(whiskey)
            }
        }
    }

    fun clearDatabase() {
        uiScope.launch {
            withContext(Dispatchers.IO){
                database.clear()
            }
        }
    }
}