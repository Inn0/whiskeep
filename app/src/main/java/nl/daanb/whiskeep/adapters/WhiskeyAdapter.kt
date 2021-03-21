package nl.daanb.whiskeep.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_whiskey.view.*
import nl.daanb.whiskeep.R
import nl.daanb.whiskeep.models.Whiskey

class WhiskeyAdapter(
    private val whiskeys: MutableList<Whiskey>
): RecyclerView.Adapter<WhiskeyAdapter.WhiskeyViewHolder>() {
    class WhiskeyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WhiskeyViewHolder {
        return WhiskeyViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_whiskey,
                    parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: WhiskeyViewHolder, position: Int) {
        val currentWhiskey = whiskeys[position]
        holder.itemView.apply {
            tv_whiskey_title.text = currentWhiskey.name
            tv_whiskey_type.text = currentWhiskey.type
            tv_whiskey_price.text = "€" + currentWhiskey.price.toString()
            tv_whiskey_abv.text = currentWhiskey.abv.toString() + "%"
            tv_whiskey_rating.text = currentWhiskey.rating.toString()
        }
    }

    override fun getItemCount(): Int {
        return whiskeys.size
    }
}