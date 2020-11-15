package com.veronica.idn.githubapp

import android.os.Parcel
import android.os.Parcelable

data class User(
    var photo: Int,
    var name: String?,
    var username : String?,
    var location: String?,
    var company: String?,
    var follower: String?,
    var following: String?,
    var repository: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(photo)
        parcel.writeString(name)
        parcel.writeString(username)
        parcel.writeString(location)
        parcel.writeString(company)
        parcel.writeString(follower)
        parcel.writeString(following)
        parcel.writeString(repository)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}