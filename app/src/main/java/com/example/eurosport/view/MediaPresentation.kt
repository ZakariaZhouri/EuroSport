package com.example.eurosport.view

import android.os.Parcel
import android.os.Parcelable

data class MediaPresentation(
    var teaser: String? = null,
    var image: String? = null,
    var author: String? = null,
    var networkId: Long? = null,
    var thumb: String? = null,
    var videoUrl: String? = null,
    var title: String? = null,
    var date: Float? = null,
    var sportId: Long? = null,
    var views: Long? = null,
    var sportName: String? = null,
    var mediaType: MediaType? = MediaType.STORIE
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Float::class.java.classLoader) as? Float,
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(teaser)
        parcel.writeString(image)
        parcel.writeString(author)
        parcel.writeValue(networkId)
        parcel.writeString(thumb)
        parcel.writeString(videoUrl)
        parcel.writeString(title)
        parcel.writeValue(date)
        parcel.writeValue(sportId)
        parcel.writeValue(views)
        parcel.writeString(sportName)
        parcel.writeString(mediaType?.name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MediaPresentation> {
        override fun createFromParcel(parcel: Parcel): MediaPresentation {
            return MediaPresentation(parcel)
        }

        override fun newArray(size: Int): Array<MediaPresentation?> {
            return arrayOfNulls(size)
        }
    }
}

enum class MediaType {
    STORIE, VIDEO
}
