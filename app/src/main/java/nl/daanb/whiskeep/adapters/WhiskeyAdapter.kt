package nl.daanb.whiskeep.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nl.daanb.whiskeep.models.Whiskey

class WhiskeyAdapter(
    private val whiskeys: MutableList<Whiskey>
): RecyclerView.Adapter<WhiskeyAdapter.WhiskeyViewHolder>() {
    class WhiskeyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WhiskeyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: WhiskeyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return whiskeys.size
    }
}