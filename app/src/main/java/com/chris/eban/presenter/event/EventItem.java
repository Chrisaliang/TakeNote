package com.chris.eban.presenter.event;

import android.os.Parcel;
import android.os.Parcelable;

public class EventItem implements Parcelable {

    public long id;

    public String title;

    public String content;

    protected EventItem(Parcel in) {
        id = in.readLong();
        title = in.readString();
        content = in.readString();
    }

    public EventItem(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(content);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<EventItem> CREATOR = new Creator<EventItem>() {
        @Override
        public EventItem createFromParcel(Parcel in) {
            return new EventItem(in);
        }

        @Override
        public EventItem[] newArray(int size) {
            return new EventItem[size];
        }
    };

    @Override
    public String toString() {
        return "EventItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
