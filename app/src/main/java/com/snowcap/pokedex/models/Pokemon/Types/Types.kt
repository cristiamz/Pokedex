package com.snowcap.pokedex.models.Pokemon.Types

import android.os.Parcel
import android.os.Parcelable

data class Types(
    val slot: Int,
    val type: List<Type>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.createTypedArrayList(Type)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(slot)
        parcel.writeTypedList(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Types> {
        override fun createFromParcel(parcel: Parcel): Types {
            return Types(parcel)
        }

        override fun newArray(size: Int): Array<Types?> {
            return arrayOfNulls(size)
        }
    }
}