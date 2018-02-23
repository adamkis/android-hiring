package at.allaboutapps.a3hiring.api.models

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Club(val name: String,
                val country: String,
                val value: Long,
                val image: String?) : Parcelable{

    companion object {
        var COMPARE_BY_NAME: Comparator<Club> = Comparator { one, other -> one.name.compareTo(other.name) }
        var COMPARE_BY_VALUE: Comparator<Club> = Comparator { one, other -> one.value.compareTo(other.value) }
    }

}