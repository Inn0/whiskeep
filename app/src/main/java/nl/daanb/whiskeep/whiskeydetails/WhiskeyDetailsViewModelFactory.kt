package nl.daanb.whiskeep.whiskeydetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import nl.daanb.whiskeep.database.WhiskeyDatabaseDao
import nl.daanb.whiskeep.newwhiskey.NewWhiskeyViewModel

class WhiskeyDetailsViewModelFactory(private val whiskeyId: Long, private val dataSource: WhiskeyDatabaseDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WhiskeyDetailsViewModel::class.java)){
            return WhiskeyDetailsViewModel(whiskeyId, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModelClass")
    }
}