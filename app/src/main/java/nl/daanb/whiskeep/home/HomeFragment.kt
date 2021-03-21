package nl.daanb.whiskeep.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import nl.daanb.whiskeep.R
import nl.daanb.whiskeep.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.rvWhiskeys.adapter = viewModel.whiskeyAdapter
        binding.rvWhiskeys.layoutManager = LinearLayoutManager(activity)

        return binding.root
    }
}