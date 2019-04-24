package com.chris.eban.presenter.event

import android.os.Parcel
import android.os.Parcelable

class EventItem : Parcelable {
    var id: Long = 0
    var title: String? = null
    var content: String? = null

    protected constructor(`in`: Parcel) {
        id = `in`.readLong()
        title = `in`.readString()
        content = `in`.readString()
    }

    constructor(title: String, content: String) {
        this.title = title
        this.content = content
    }

    constructor(id: Long, title: String?, content: String?) {
        this.id = id
        this.title = title
        this.content = content
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeString(content)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EventItem> {
        override fun createFromParcel(parcel: Parcel): EventItem {
            return EventItem(parcel)
        }

        override fun newArray(size: Int): Array<EventItem?> {
            return arrayOfNulls(size)
        }
    }


}
