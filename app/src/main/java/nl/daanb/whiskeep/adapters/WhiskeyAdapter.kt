package nl.daanb.whiskeep.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_whiskey.view.*
import nl.daanb.whiskeep.R
import nl.daanb.whiskeep.home.HomeViewModel
import nl.daanb.whiskeep.models.Whiskey

class WhiskeyAdapter(
    private val whiskeys: List<Whiskey>, private val viewModel: HomeViewModel
): RecyclerView.Adapter<WhiskeyAdapter.WhiskeyViewHolder>() {
    class WhiskeyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    private var whiskeyList: List<Whiskey> = whiskeys



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
        val currentWhiskey = whiskeyList[position]
        holder.itemView.apply {
            tv_whiskey_title.text = currentWhiskey.name
            tv_whiskey_type.text = currentWhiskey.type
            tv_whiskey_price.text = "€" + currentWhiskey.price.toString()
            tv_whiskey_abv.text = currentWhiskey.abv.toString() + "%"
            tv_whiskey_rating.text = currentWhiskey.rating.toString()
            iv_whiskey_delete.setOnClickListener {
                viewModel.deleteWhiskey(currentWhiskey)
                notifyItemRemoved(position)
            }
            Glide.with(this).load(currentWhiskey.image).into(iv_whiskey_image)
        }
    }

    override fun getItemCount(): Int {
        return whiskeyList.size
    }

    fun setData(newData: List<Whiskey>){
        whiskeyList = newData
        notifyDataSetChanged()
    }

}