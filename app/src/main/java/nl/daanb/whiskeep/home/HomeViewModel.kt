package nl.daanb.whiskeep.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import nl.daanb.whiskeep.adapters.WhiskeyAdapter
import nl.daanb.whiskeep.models.Whiskey

class HomeViewModel(application: Application): AndroidViewModel(application) {
    val whiskeyAdapter: WhiskeyAdapter = WhiskeyAdapter(mutableListOf(
            Whiskey(0, "Laphroaig", "Irish Whiskey" ,40F, 42.50F, listOf("Smoky", "Maritime"), 75),
            Whiskey(0, "Four Roses", "Bourbon",40F, 22.50F, listOf("test"), 80)
    ))
}