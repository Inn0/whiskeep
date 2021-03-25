package nl.daanb.whiskeep.whiskeydetails

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import nl.daanb.whiskeep.database.WhiskeyDatabaseDao
import nl.daanb.whiskeep.models.Whiskey

/**
 * ViewModel for the details page. It provides the functionality of reading, editing and deleting the data of one specific whiskey.
 *
 * @param whiskeyId The ID of the whiskey that was selected
 * @param database The DAO for the whiskey database
 *
 * @author Daan Brocatus
 */
class WhiskeyDetailsViewModel(val whiskeyId: Long, val database: WhiskeyDatabaseDao) : ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var selectedWhiskey = database.get(whiskeyId)

    private val _details = MutableLiveData<Int>()
    val details: LiveData<Int> get() = _details

    private val _editing = MutableLiveData<Int>()
    val editing: LiveData<Int> get() = _editing

    init {
        _details.value = View.VISIBLE
        _editing.value = View.GONE
    }

    fun udpateWhiskey(whiskey: Whiskey){
        val updatedWhiskey: Whiskey = whiskey
        whiskey.whiskeyId = selectedWhiskey.value?.whiskeyId!!
        uiScope.launch {
            withContext(Dispatchers.IO){
                database.update(updatedWhiskey)
            }
        }
    }

    fun deleteWhiskey(){
        uiScope.launch {
            withContext(Dispatchers.IO){
                database.delete(selectedWhiskey.value!!)
            }
        }
    }

    fun toggleVisibility() {
        if(_details.value == View.VISIBLE){
            _details.value = View.GONE
            _editing.value = View.VISIBLE
        } else {
            _details.value = View.VISIBLE
            _editing.value = View.GONE
        }
    }
}