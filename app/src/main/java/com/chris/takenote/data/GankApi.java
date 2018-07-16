package com.chris.takenote.data;

import retrofit2.http.GET;

/**
 * a mock server
 */
public interface GankApi {

    @GET("vv")
    void everyDayPic();
}
