package nl.daanb.whiskeep.newwhiskey

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import nl.daanb.whiskeep.database.WhiskeyDatabaseDao

/**
 * Injects the DAO into the ViewModel
 *
 * @param dataSource the DAO
 *
 * @author Daan Brocatus
 */
class NewWhiskeyViewmodelFactory(private val dataSource: WhiskeyDatabaseDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NewWhiskeyViewModel::class.java)){
            return NewWhiskeyViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModelClass")
    }
}