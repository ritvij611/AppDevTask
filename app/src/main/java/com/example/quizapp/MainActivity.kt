package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.widget.AppCompatEditText
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizapp.ui.theme.QuizAppTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val startButton = findViewById<Button>(R.id.startButton)
        val username = findViewById<AppCompatEditText>(R.id.userName)
        startButton.setOnClickListener{
            if(username.text.toString().isEmpty()){
                Toast.makeText(this,"Please enter user name for quiz", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this,QuizInterface::class.java)
                intent.putExtra(ListsOfQuestion.user_name,username.text)
                startActivity(intent)
                finish()
            }
        }
    }
}
