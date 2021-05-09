package com.gal.countryapi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gal.countryapi.network.ApiService
import com.gal.countryapi.network.Country
import com.gal.countryapi.network.CountryApi
import com.gal.countryapi.network.SortCountry
import kotlinx.coroutines.launch
import java.lang.Exception
lateinit var list: List<Country>

class MainViewModel: ViewModel() {
    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> get() = _countries

    private val _navigateToSelectedCountry = MutableLiveData<Country>()
    val navigateToSelectedCountry: LiveData<Country> get() = _navigateToSelectedCountry

    init {
        getCountriesList()
    }

    private fun getCountriesList() {

        viewModelScope.launch {
            try {
                list = CountryApi.retrofitService.getAll()
                _countries.value = list

            } catch (e: Exception) {
                _countries.value = ArrayList()
            }
        }
    }

    fun sortCountryBy(sort: SortCountry) {
       when (sort) {
           SortCountry.SORT_A_Z -> {_countries.value = list.sortedBy { it.name } }
           SortCountry.SORT_Z_A -> {_countries.value = list.sortedByDescending { it.name }}
           SortCountry.SORT_SMALLEST -> {_countries.value = list.sortedBy { it.area }}
           SortCountry.SORT_GREATEST -> {_countries.value = list.sortedByDescending { it.area }}
       }

    }

    fun displayCountrySelected(country: Country) {
        _navigateToSelectedCountry.value = country
    }

    fun displayCountryComplete() {
        _navigateToSelectedCountry.value = null
    }

    fun getFullList() : List<Country> {

        return _countries.value!!
    }
}