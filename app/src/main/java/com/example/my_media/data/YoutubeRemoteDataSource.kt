package com.example.my_media.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface YoutubeRemoteDataSource {
    @GET("youtube/v3/subscriptions")
    suspend fun getSubscribe(
        @Header("Authorization") token: String,
        @Query("part") part: String = "snippet",
        @Query("maxResults") maxResults: Int = 10,
        @Query("mine") subscribe: Boolean = true,
        @Query("order") order: String = "unread"
    ) : SubscribeResponse

    @GET("youtube/v3/videos")
    suspend fun getPopularVideo(
        @Query("part") part : String = "snippet",
        @Query("chart") chart : String = "mostPopular",
        @Query("maxResults") maxResults : Int = 20,
        @Query("regionCode") regionCode : String = "KR",
//        @Query("videoCategoryId") videoCategoryId : String,
        @Query("key") key: String
    ) : PopularVideosResponse

    @GET("youtube/v3/search")
    suspend fun getSearchVideo(
        @Query("part") part: String,
        @Query("chart") chart: String?,
        @Query("maxResults") maxResults: Int?,
        @Query("regionCode") regionCode: String?,
        @Query("key") apiKey: String
    ): SearchVideoResponse
}