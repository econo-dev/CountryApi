package com.gal.countryapi.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country(
    val name: String,
    val nativeName: String,
    val alpha3Code: String,
    @Json(name = "area") var area: Double?,
    var borders: List<String>?
) : Parcelable {

    var areaFormatted: Double
        get() = area?:0.0
        set(value) {
            area = value
    }


    override fun toString(): String {
        if  (areaFormatted == 0.0) {
                return "No Data"
        }
        return "area: "+areaFormatted
    }




}

