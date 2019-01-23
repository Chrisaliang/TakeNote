package com.chris.eban.data.http

import com.chris.eban.data.pojo.Beauty
import com.chris.eban.data.pojo.CommonResult
import retrofit2.Call
import retrofit2.http.GET

/**
 * a mock server
 */
interface GankApi {

    @GET("api/data/\\u798f\\u5229/10/1")
    fun everyDayPic(): Call<CommonResult<List<Beauty>>>
}
