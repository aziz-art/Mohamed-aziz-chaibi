// RecipeData.kt
package com.example.recipi

import android.os.Parcel
import android.os.Parcelable

data class RecipeData(val imgResId: Int, val name: String, val duration: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(imgResId)
        parcel.writeString(name)
        parcel.writeString(duration)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<RecipeData> {
        override fun createFromParcel(parcel: Parcel): RecipeData {
            return RecipeData(parcel)
        }

        override fun newArray(size: Int): Array<RecipeData?> {
            return arrayOfNulls(size)
        }
    }
}
