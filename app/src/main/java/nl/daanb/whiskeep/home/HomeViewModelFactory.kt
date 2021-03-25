package nl.daanb.whiskeep.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import nl.daanb.whiskeep.database.WhiskeyDatabaseDao

/**
 * Injects the Data Access Object into the HomeViewModel when it is created.
 *
 * @param dataSource The DAO
 *
 * @author Daan Brocatus
 */
class HomeViewModelFactory(private val dataSource: WhiskeyDatabaseDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModelClass")
    }
}