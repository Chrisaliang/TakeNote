package com.chris.eban.presenter.event;

public class EventItem {
    public String title;
    public String content;

    @Override
    public String toString() {
        return "EventItem{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
