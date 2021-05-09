package com.gal.countryapi

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gal.countryapi.network.Country
import com.gal.countryapi.ui.CountryRecyclerViewAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Country>?) {
    val adapter = recyclerView.adapter as CountryRecyclerViewAdapter
    adapter.submitList(data)
}
