package com.example.randomusergenerator.data.local

import android.os.Parcelable
import com.example.randomusergenerator.data.remote.dto.Location
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LocationData(
    val street: StreetData? = null,
    val city: String? = null,
    val state: String? = null,
    val country: String? = null
) : Parcelable {
    companion object {
        fun from(location: Location?): LocationData {
            return LocationData(
                street = StreetData.from(location?.street),
                city = location?.city,
                state = location?.state,
                country = location?.country
            )
        }
    }
}
