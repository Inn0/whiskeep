package nl.daanb.whiskeep.whiskeydetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_new_whiskey.*
import kotlinx.android.synthetic.main.fragment_whiskey_details.*
import nl.daanb.whiskeep.R
import nl.daanb.whiskeep.database.WhiskeyDatabase
import nl.daanb.whiskeep.databinding.FragmentWhiskeyDetailsBinding
import nl.daanb.whiskeep.models.Whiskey
import nl.daanb.whiskeep.newwhiskey.NewWhiskeyFragmentDirections
import java.util.*

class WhiskeyDetailsFragment : Fragment() {
    private lateinit var viewModel: WhiskeyDetailsViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentWhiskeyDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_whiskey_details, container, false)

        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application
        val arguments = WhiskeyDetailsFragmentArgs.fromBundle(arguments!!)
        val viewModelFactory = WhiskeyDetailsViewModelFactory(arguments.whiskeyId, WhiskeyDatabase.getInstance(application).whiskeyDatabaseDao)
        viewModel = ViewModelProvider(this, viewModelFactory).get(WhiskeyDetailsViewModel::class.java)

        binding.viewModel = viewModel

        binding.btnWhiskeydetailsClose.setOnClickListener {
            findNavController().navigate(WhiskeyDetailsFragmentDirections.actionWhiskeyDetailsFragmentToHomeFragment())
        }

        viewModel.selectedWhiskey.observe(viewLifecycleOwner, Observer { whiskey ->
            whiskey?.let {
                Glide.with(this).load(viewModel.selectedWhiskey.value?.image.toString()).placeholder(R.drawable.ic_baseline_local_bar_24).into(binding.ivWhiskeydetailsImage)
            }
        })

        binding.buttonSave.setOnClickListener {
            it.hideKeyboard()
            if(et_details_name.text.toString().isNotEmpty() && et_details_type.text.toString().isNotEmpty() && et_details_abv.text.toString().isNotEmpty() && et_details_price.text.toString().isNotEmpty() && et_details_rating.text.toString().isNotEmpty()){
                if(et_details_rating.text.toString().toInt() in 1..100){
                    if(et_details_abv.text.toString().toFloat() in 20F..60F){
                        val updatedWhiskey = Whiskey(
                            (et_details_name.text.toString()).capitalize(Locale.ROOT),
                            (et_details_type.text.toString()).capitalize(Locale.ROOT),
                            et_details_abv.text.toString().toFloat(),
                            et_details_price.text.toString().toFloat(),
                            et_details_notes.text.toString(),
                            et_details_rating.text.toString().toInt(),
                            et_details_img.text.toString(),
                            et_details_area.text.toString()
                        )
                        viewModel.udpateWhiskey(updatedWhiskey)
                        viewModel.toggleVisibility()
                    } else {
                        showSnackbar(R.string.too_much_alcohol)
                    }
                } else {
                    showSnackbar(R.string.out_of_bounds)
                }
            } else {
                showSnackbar(R.string.empty_fields)
            }
        }
        
        return binding.root
    }

    private fun showSnackbar(stringRes: Int){
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            getString(stringRes),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}