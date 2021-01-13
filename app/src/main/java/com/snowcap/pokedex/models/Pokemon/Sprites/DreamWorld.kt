package com.snowcap.pokedex.models.Pokemon.Sprites

import android.os.Parcel
import android.os.Parcelable

data class DreamWorld(

    val front_default: String,
    val front_female: String
    ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(front_default)
        parcel.writeString(front_female)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DreamWorld> {
        override fun createFromParcel(parcel: Parcel): DreamWorld {
            return DreamWorld(parcel)
        }

        override fun newArray(size: Int): Array<DreamWorld?> {
            return arrayOfNulls(size)
        }
    }
}