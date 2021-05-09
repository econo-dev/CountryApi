package com.gal.countryapi.ui

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.gal.countryapi.databinding.CountryItemBinding
import com.gal.countryapi.network.Country

/**
 * [RecyclerView.Adapter] that can display a [Country].
 */

class CountryRecyclerViewAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Country, CountryRecyclerViewAdapter.ViewHolder>(DiffCallback) {

    private val TAG: String = javaClass.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(CountryItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
            Log.e(TAG, getItem(position).nativeName)
        }
        holder.bind(item)
    }


    class ViewHolder(private var binding: CountryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(country: Country) {
                binding.country = country
                binding.executePendingBindings()
            }

    }

    class OnClickListener(val clickListener: (country: Country) -> Unit) {
        fun onClick(country: Country) = clickListener(country)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.alpha3Code == newItem.alpha3Code
        }
    }
}