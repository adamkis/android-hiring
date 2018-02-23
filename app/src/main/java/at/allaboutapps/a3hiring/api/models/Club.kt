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
        val COMPARE_BY_NAME_ASC: Comparator<Club> = Comparator { one, other -> one.name.compareTo(other.name) }
        val COMPARE_BY_VALUE_DESC: Comparator<Club> = Comparator { one, other -> -1 * one.value.compareTo(other.value) }
    }

}