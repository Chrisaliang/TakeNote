package com.chris.takenote.data.http;

import com.chris.takenote.data.pojo.CommonResult;
import com.chris.takenote.data.pojo.Beauty;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * a mock server
 */
public interface GankApi {

    @GET("api/data/\\u798f\\u5229/10/1")
    Call<CommonResult<List<Beauty>>> everyDayPic();
}
