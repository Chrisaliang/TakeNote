package com.chris.takenote.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Beauty {
    @SerializedName("_id")
    public String id;

    @SerializedName("createdAt")
    public Date createTime;

    @SerializedName("desc")
    public String desc;

    @SerializedName("publishedAt")
    public Date publishTime;

    @SerializedName("source")
    public String source;

    @SerializedName("type")
    public String type;

    @SerializedName("url")
    public String imgUrl;

    @SerializedName("used")
    public boolean used;

    @SerializedName("who")
    public String author;
}
