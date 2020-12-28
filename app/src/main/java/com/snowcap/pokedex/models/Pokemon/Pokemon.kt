package com.snowcap.pokedex.models.Pokemon
import android.os.Parcel
import android.os.Parcelable
import com.snowcap.pokedex.models.Pokemon.Types.Types
import com.snowcap.pokedex.models.Pokemon.Sprites.Sprites

data class Pokemon(
    val id: Int,
    val name: String,
    val base_experience: String,
    val height: Int,
    val is_default: Boolean,
    val order: Int,
    val weight: Int,
    val location_area_encounters: String,
    //abilities
    //forms
    //game_indices
    //held_items
    //moves
    //species
    val sprites: Sprites,
    //stats
    val types : List<Types>,
    val isFavorite:Boolean ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readParcelable(Sprites::class.java.classLoader)!!,
        parcel.createTypedArrayList(Types)!!,
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(base_experience)
        parcel.writeInt(height)
        parcel.writeByte(if (is_default) 1 else 0)
        parcel.writeInt(order)
        parcel.writeInt(weight)
        parcel.writeString(location_area_encounters)
        parcel.writeParcelable(sprites, flags)
        parcel.writeTypedList(types)
        parcel.writeByte(if (isFavorite) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pokemon> {
        override fun createFromParcel(parcel: Parcel): Pokemon {
            return Pokemon(parcel)
        }

        override fun newArray(size: Int): Array<Pokemon?> {
            return arrayOfNulls(size)
        }
    }
}