package nl.daanb.whiskeep.adapters


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_whiskey.view.*
import nl.daanb.whiskeep.R
import nl.daanb.whiskeep.home.HomeViewModel
import nl.daanb.whiskeep.models.Whiskey

/**
 * The adapter used on the home screen to display the whiskeys.
 *
 * @author Daan Brocatus
 *
 * @param whiskeys A list of Whikeys
 * @param viewModel The associated viewmodel
 */
class WhiskeyAdapter(
    whiskeys: List<Whiskey>, private val viewModel: HomeViewModel
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

    // Suppress hints about string formatting as they are irrelevant for the functionality
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: WhiskeyViewHolder, position: Int) {
        whiskeyList = whiskeyList.sortedByDescending { it.rating }
        val currentWhiskey = whiskeyList[position]
        holder.itemView.apply {
            tv_whiskey_title.text = currentWhiskey.name
            tv_whiskey_type.text = currentWhiskey.type
            tv_whiskey_price.text = "€" + currentWhiskey.price.toString()
            tv_whiskey_abv.text = currentWhiskey.abv.toString() + "%"
            tv_whiskey_rating.text = currentWhiskey.rating.toString()
            Glide.with(this).load(currentWhiskey.image).placeholder(R.drawable.ic_baseline_local_bar_24).into(iv_whiskey_image)
            setOnClickListener {
                viewModel.startNavigation(currentWhiskey.whiskeyId)
            }
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