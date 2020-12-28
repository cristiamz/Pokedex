package com.snowcap.pokedex.models.Pokemon.Types

import android.os.Parcel
import android.os.Parcelable

data class Types(
    val slot: Int,
    val type: Type
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readParcelable(Type::class.java.classLoader)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(slot)
        parcel.writeParcelable(type, flags)
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