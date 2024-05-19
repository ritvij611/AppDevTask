package com.example.quizapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestionsInterface {

    @GET("/api.php")
    fun getData(@Query("amount") query1: Int, @Query("type") query2: String) : Call<DataFromApi?>

}