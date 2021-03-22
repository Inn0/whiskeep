package nl.daanb.whiskeep.newwhiskey

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_new_whiskey.*
import nl.daanb.whiskeep.R
import nl.daanb.whiskeep.database.WhiskeyDatabase
import nl.daanb.whiskeep.databinding.FragmentNewWhiskeyBinding
import nl.daanb.whiskeep.home.HomeViewModelFactory
import nl.daanb.whiskeep.models.Whiskey

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
            val newWhiskey = Whiskey(
                    et_whiskey_name.text.toString(),
                    et_whiskey_type.text.toString(),
                    et_whiskey_abv.text.toString().toFloat(),
                    et_whiskey_price.text.toString().toFloat(),
                    et_whiskey_notes.text.toString(),
                    et_whiskey_rating.text.toString().toInt()
            )
            viewModel.saveWhiskey(newWhiskey)
            findNavController().navigate(NewWhiskeyFragmentDirections.actionNewWhiskeyFragmentToHomeFragment())
        }

        return binding.root
    }
}