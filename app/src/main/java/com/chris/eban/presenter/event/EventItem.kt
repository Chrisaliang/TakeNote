package com.chris.eban.presenter.event

import android.os.Parcel
import java.util.*

class EventItem() {
    var id: Long = 0
    var title: String? = null
    var content: String? = null
    var updateTime: String? = "todo"
    var createTime: String? = ""
    var createDate: Date = Date()

    constructor(parcel: Parcel) : this() {
        id = parcel.readLong()
        title = parcel.readString()
        content = parcel.readString()
        updateTime = parcel.readString()
        createTime = parcel.readString()
    }

    constructor(title: String, content: String) : this() {
        this.title = title
        this.content = content
    }

}
