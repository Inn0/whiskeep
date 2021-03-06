package nl.daanb.whiskeep.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import nl.daanb.whiskeep.R
import nl.daanb.whiskeep.adapters.WhiskeyAdapter
import nl.daanb.whiskeep.database.WhiskeyDatabase
import nl.daanb.whiskeep.databinding.FragmentHomeBinding

/**
 * The 'home' page. gives the user an overview of all their whiskeys and the ability to completely clear the database.
 *
 * @author Daan Brocatus
 */
class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)

        val binding: FragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = HomeViewModelFactory(WhiskeyDatabase.getInstance(application).whiskeyDatabaseDao)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        val whiskeyAdapter = WhiskeyAdapter(listOf(), viewModel)

        binding.rvWhiskeys.adapter = whiskeyAdapter
        binding.rvWhiskeys.layoutManager = LinearLayoutManager(activity)

        viewModel.whiskeys.observe(viewLifecycleOwner, { whiskeys ->
            whiskeyAdapter.setData(whiskeys)
        })

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNewWhiskeyFragment())
        }

        viewModel.navigateToDetails.observe(viewLifecycleOwner, Observer { whiskey ->
            whiskey?.let {
                this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToWhiskeyDetailsFragment(viewModel.selectedWhiskeyId))
                viewModel.doneNavigation()
            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.kebab_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_delete -> {
                viewModel.clearDatabase()
                Snackbar.make(
                        requireActivity().findViewById(android.R.id.content),
                        getString(R.string.cleared_message),
                        Snackbar.LENGTH_SHORT
                ).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}