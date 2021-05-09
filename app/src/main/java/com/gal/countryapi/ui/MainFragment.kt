package com.gal.countryapi.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gal.countryapi.R
import com.gal.countryapi.databinding.FragmentMainBinding
import com.gal.countryapi.network.SortCountry
import com.gal.countryapi.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*


/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {

    companion object val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the layout with Data binding, set MainFragment as lifecycle owner
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.rvList.adapter = CountryRecyclerViewAdapter(CountryRecyclerViewAdapter.OnClickListener{
            viewModel.displayCountrySelected(it)
        })
        viewModel.navigateToSelectedCountry.observe(this, Observer {
            if (null != it) {
                this.findNavController().navigate(MainFragmentDirections.actionShowDetail(it))
                viewModel.displayCountryComplete()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    /**
     * Inflates the overflow menu that contains filtering options.
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    /**
     * Updates the filter in the [MainViewModel] when the menu items are selected from the
     * overflow menu.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.sortCountryBy(
            when (item.itemId) {
                R.id.sort_ascend -> { SortCountry.SORT_A_Z }
                R.id.sort_descend -> SortCountry.SORT_Z_A
                R.id.sort_smallest -> { SortCountry.SORT_SMALLEST}
                R.id.sort_greatest -> SortCountry.SORT_GREATEST
                else -> { return false}
            }
        )
        return true
    }
}