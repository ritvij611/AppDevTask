package com.example.quizapp

data class Question(
    val serial: Int,
    val question: String,
    val category: String,
    val difficulty: String,
    val op1: String,
    val op2: String,
    val op3: String,
    val op4: String,
    val correctOption: Int
    ){


}