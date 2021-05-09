package com.gal.countryapi.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gal.countryapi.network.Country

class CountryViewModelFactory (
    private val country: Country,
    private val list: List<Country>,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryViewModel::class.java)) {
            return CountryViewModel(country, list, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}