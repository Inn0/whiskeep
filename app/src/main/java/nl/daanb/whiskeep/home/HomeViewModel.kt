package nl.daanb.whiskeep.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import nl.daanb.whiskeep.database.WhiskeyDatabaseDao
import nl.daanb.whiskeep.models.Whiskey

class HomeViewModel(private val database: WhiskeyDatabaseDao): ViewModel() {
    var whiskeys : LiveData<List<Whiskey>> = database.getAllWhiskeys()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var selectedWhiskeyId: Long = 0L

    private val _navigateToDetails = MutableLiveData<Long>()
    val navigateToDetails: LiveData<Long> get() = _navigateToDetails

    fun doneNavigation(){
        _navigateToDetails.value = null
    }

    fun startNavigation(id: Long){
        this.selectedWhiskeyId = id
        _navigateToDetails.value = id
    }

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