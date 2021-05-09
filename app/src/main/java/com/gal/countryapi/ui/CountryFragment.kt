package com.gal.countryapi.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDestination
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.gal.countryapi.R
import com.gal.countryapi.bindRecyclerView
import com.gal.countryapi.databinding.FragmentCountryBinding
import com.gal.countryapi.network.Country
import com.gal.countryapi.network.SortCountry
import com.gal.countryapi.viewmodel.CountryViewModel
import com.gal.countryapi.viewmodel.CountryViewModelFactory
import com.gal.countryapi.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_country.*
import kotlinx.android.synthetic.main.fragment_country.view.*
import kotlinx.coroutines.launch
import java.lang.Exception


class CountryFragment : Fragment() {

    companion object val countryViewModel: CountryViewModel by lazy {
        ViewModelProvider(this).get(CountryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {


        val application = requireNotNull(activity).application
        val binding = FragmentCountryBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val country = CountryFragmentArgs.fromBundle(arguments!!).selectedCountry


        var countryList: List<Country> = ArrayList()
        val viewModelFactory = CountryViewModelFactory(country, countryList, application)



        binding.viewModel = ViewModelProvider(
            this, viewModelFactory).get(CountryViewModel::class.java)
        countryViewModel.countries.observe(this, Observer {
            countryList = countryViewModel.countries.value!!
        })



        binding.rvBorders.adapter = CountryRecyclerViewAdapter(CountryRecyclerViewAdapter.OnClickListener {
                countryViewModel.showBorderingCountries(it)
        })
        countryViewModel.selectedCountry.observe(this, Observer {

            if (null != it) {
                countryViewModel.displayCountryComplete()
            }
        })

        return binding.root
    }

}