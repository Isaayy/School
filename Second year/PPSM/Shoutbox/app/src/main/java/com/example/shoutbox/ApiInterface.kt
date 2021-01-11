package com.example.shoutbox

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @get:GET("messages")
    val posts: Call<List<PostModel?>?>?

    companion object {
        val base_url = "http://tgryl.pl/shoutbox/"
    }
}