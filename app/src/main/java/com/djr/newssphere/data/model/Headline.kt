package com.djr.newssphere.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "headlines")
data class Headline(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("urlToImage")
    val imageUrl: String?,
    @SerializedName("publishedAt")
    val date: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(content)
        parcel.writeString(imageUrl)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Headline> {
        override fun createFromParcel(parcel: Parcel): Headline {
            return Headline(parcel)
        }

        override fun newArray(size: Int): Array<Headline?> {
            return arrayOfNulls(size)
        }
    }
}