package at.allaboutapps.a3hiring.api.models

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Club(val name: String,
                val country: String,
                val value: Long,
                val image: String?) : Parcelable