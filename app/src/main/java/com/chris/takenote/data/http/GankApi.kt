package com.chris.takenote.data.http

import com.chris.takenote.data.pojo.Beauty
import com.chris.takenote.data.pojo.CommonResult
import retrofit2.Call
import retrofit2.http.GET

/**
 * a mock server
 */
interface GankApi {

    @GET("api/data/\\u798f\\u5229/10/1")
    fun everyDayPic(): Call<CommonResult<List<Beauty>>>
}
