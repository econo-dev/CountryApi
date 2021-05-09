package com.gal.countryapi.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.gal.countryapi.network.Country

class CountryViewModel( country: Country, countryList: List<Country>, app: Application): AndroidViewModel(app) {
    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> get() = _countries

    private val _selectedCountry = MutableLiveData<Country>()
    val selectedCountry: LiveData<Country> get() = _selectedCountry

    private val _bordering = MutableLiveData<List<Country>>()
    val bordering: LiveData<List<Country>> get() = _bordering
    var borders = mutableListOf<Country>()

    private val _isBorders = MutableLiveData<String>()
    val isBorders: LiveData<String> get() = _isBorders

    init {
        _selectedCountry.value = country
        _countries.value = list
        showBorderingCountries(country)
    }

    fun showBorderingCountries(country: Country) {

        Log.e("CVM", _countries.value.toString())

        for ((i, c) in list.withIndex()) {
            if (country.borders?.contains(c.alpha3Code)!!) {
                borders.add(c)
            }
        }
        _bordering.value = borders

    }

    fun displayCountrySelected(country: Country) {
        _selectedCountry.value = country
    }

    fun displayCountryComplete() {
        _selectedCountry.value = null
    }

}