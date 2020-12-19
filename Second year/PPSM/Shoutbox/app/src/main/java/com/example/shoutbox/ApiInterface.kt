package com.example.shoutbox

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @get:GET("messages")
    val posts: Call<List<PostModel?>?>?

    companion object {
        val base_url = "http://tgryl.pl/shoutbox/"
    }
}