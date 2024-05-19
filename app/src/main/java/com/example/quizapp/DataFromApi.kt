package com.example.quizapp

data class DataFromApi(
    val response_code: Int,
    val results: List<ResultX>
)