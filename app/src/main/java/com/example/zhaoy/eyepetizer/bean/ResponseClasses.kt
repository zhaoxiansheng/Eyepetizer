package com.example.zhaoy.eyepetizer.bean

import android.os.Parcel
import android.os.Parcelable

class ResponseClasses {
    data class Categories(val id: Int, val name: String, val alias: String, val description: String,
                          val bgPicture: String, val bgColor: String, val headerImage: String,
                          val defaultAuthorId: String) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString())

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(id)
            parcel.writeString(name)
            parcel.writeString(alias)
            parcel.writeString(description)
            parcel.writeString(bgPicture)
            parcel.writeString(bgColor)
            parcel.writeString(headerImage)
            parcel.writeString(defaultAuthorId)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Categories> {
            override fun createFromParcel(parcel: Parcel): Categories {
                return Categories(parcel)
            }

            override fun newArray(size: Int): Array<Categories?> {
                return arrayOfNulls(size)
            }
        }
    }
}