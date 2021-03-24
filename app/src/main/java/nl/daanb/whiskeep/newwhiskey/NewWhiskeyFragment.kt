package nl.daanb.whiskeep.newwhiskey

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_new_whiskey.*
import nl.daanb.whiskeep.R
import nl.daanb.whiskeep.database.WhiskeyDatabase
import nl.daanb.whiskeep.databinding.FragmentNewWhiskeyBinding
import nl.daanb.whiskeep.home.HomeViewModelFactory
import nl.daanb.whiskeep.models.Whiskey
import java.lang.NumberFormatException
import java.util.*

class NewWhiskeyFragment: Fragment() {
    private lateinit var viewModel: NewWhiskeyViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentNewWhiskeyBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_whiskey, container, false)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = NewWhiskeyViewmodelFactory(WhiskeyDatabase.getInstance(application).whiskeyDatabaseDao)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NewWhiskeyViewModel::class.java)

        binding.btnWhiskeyCancel.setOnClickListener {
            findNavController().navigate(NewWhiskeyFragmentDirections.actionNewWhiskeyFragmentToHomeFragment())
        }

        binding.btnWhiskeyAdd.setOnClickListener {
            it.hideKeyboard()
            if(et_whiskey_name.text.toString().isNotEmpty() && et_whiskey_type.text.toString().isNotEmpty() && et_whiskey_abv.text.toString().isNotEmpty() && et_whiskey_price.text.toString().isNotEmpty() && et_whiskey_rating.text.toString().isNotEmpty()){
                if(et_whiskey_rating.text.toString().toInt() in 1..100){
                    if(et_whiskey_abv.text.toString().toFloat() in 20F..60F){
                        val newWhiskey = Whiskey(
                            (et_whiskey_name.text.toString()).capitalize(Locale.ROOT),
                            (et_whiskey_type.text.toString()).capitalize(Locale.ROOT),
                            et_whiskey_abv.text.toString().toFloat(),
                            et_whiskey_price.text.toString().toFloat(),
                            et_whiskey_notes.text.toString(),
                            et_whiskey_rating.text.toString().toInt(),
                            et_whiskey_img.text.toString()
                        )
                        viewModel.saveWhiskey(newWhiskey)
                        findNavController().navigate(NewWhiskeyFragmentDirections.actionNewWhiskeyFragmentToHomeFragment())
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