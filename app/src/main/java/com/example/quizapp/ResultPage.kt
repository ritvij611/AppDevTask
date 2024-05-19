package com.example.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizapp.databinding.QuizQuestionBinding
import com.example.quizapp.databinding.ResultPageBinding
import com.example.quizapp.ui.theme.QuizAppTheme

class ResultPage : ComponentActivity() {

    lateinit var binding: ResultPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ResultPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = intent.getStringExtra(ListsOfQuestion.user_name)
        val correctAns = intent.getStringExtra(ListsOfQuestion.correct_answer)
        val totalQ = intent.getStringExtra(ListsOfQuestion.total_q)


        binding.userName.text="SCORE BOARD OF $userName"
        binding.correct.text="CORRECT: $correctAns/$totalQ"



    }
}

