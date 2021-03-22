package nl.daanb.whiskeep.newwhiskey

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import nl.daanb.whiskeep.database.WhiskeyDatabaseDao

class NewWhiskeyViewmodelFactory(private val dataSource: WhiskeyDatabaseDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NewWhiskeyViewModel::class.java)){
            return NewWhiskeyViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModelClass")
    }
}