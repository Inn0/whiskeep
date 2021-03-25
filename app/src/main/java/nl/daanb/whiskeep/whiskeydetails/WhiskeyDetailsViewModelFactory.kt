package nl.daanb.whiskeep.whiskeydetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import nl.daanb.whiskeep.database.WhiskeyDatabaseDao

/**
 * Injects the whiskeyId and DAO into the WhiskeyDetailsViewModel.
 *
 * @param whiskeyId The whiskey's unique ID
 * @param dataSource The DAO
 *
 * @author Daan Brocatus
 */
class WhiskeyDetailsViewModelFactory(private val whiskeyId: Long, private val dataSource: WhiskeyDatabaseDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WhiskeyDetailsViewModel::class.java)){
            return WhiskeyDetailsViewModel(whiskeyId, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModelClass")
    }
}