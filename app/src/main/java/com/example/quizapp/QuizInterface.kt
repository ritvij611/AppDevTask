package com.example.quizapp

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.content.res.ResourcesCompat
import com.example.quizapp.databinding.QuizQuestionBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuizInterface : ComponentActivity() , View.OnClickListener
{

    lateinit var binding: QuizQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= QuizQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
    }

    private fun getData() {

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Plz wait while data is being fetched")
        progressDialog.show()
        RetrofitInstance.apiInterface.getData(10,"multiple").enqueue(object : Callback<DataFromApi?> {

            override fun onResponse(p0: Call<DataFromApi?>, p1: Response<DataFromApi?>)
            {
                progressDialog.dismiss()
                val dataList = p1.body()?.results
                val qList = dataList?.let { ListsOfQuestion.getQ(it) }
                var qOn: Int = 1
                var question: Question? = qList!![qOn-1]
                var User_name: String? = intent.getStringExtra(ListsOfQuestion.user_name)

                binding.progressBar.max = qList.size


                var correctA = question!!.correctOption
                var selected: Int = 0
                var score: Int = 0

                val myBackground: Drawable? =
                    ResourcesCompat.getDrawable(resources, R.drawable.option, null)

                fun makeNormal() {
                    binding.option1.background = myBackground
                    binding.option2.background = myBackground
                    binding.option3.background = myBackground
                    binding.option4.background = myBackground
                }

                fun setQuestion(q: Int) {
                    makeNormal()
                    question = qList?.get(q - 1)
                    binding.progressBar.progress = q
                    binding.textProgress.text = "${binding.progressBar.progress}/${binding.progressBar.max}"
                    binding.qText.text = question!!.question
                    binding.difficultyText.text = question!!.difficulty
                    binding.categoryText.text = question!!.category
                    binding.option1.text = question!!.op1
                    binding.option2.text = question!!.op2
                    binding.option3.text = question!!.op3
                    binding.option4.text = question!!.op4
                    correctA = question!!.correctOption
                    binding.submit.text="SUBMIT"
                    binding.scoreText.text="SCORE: $score/${binding.progressBar.max}"
                }

                binding.option1.setOnClickListener() {
                    makeNormal()
                    selected = 1
                    binding.option1.setBackgroundColor(resources.getColor(android.R.color.black))
                }
                binding.option2.setOnClickListener() {
                    makeNormal()
                    selected = 2
                    binding.option2.setBackgroundColor(resources.getColor(android.R.color.black))
                }
                binding.option3.setOnClickListener() {
                    makeNormal()
                    selected = 3
                    binding.option3.setBackgroundColor(resources.getColor(android.R.color.black))
                }
                binding.option4.setOnClickListener() {
                    makeNormal()
                    selected = 4
                    binding.option4.setBackgroundColor(resources.getColor(android.R.color.black))
                }

                setQuestion(qOn)

                fun correctAnswerMode(answer: Int) {
                    when (answer) {
                        1 -> {
                            binding.option1.setBackgroundColor(resources.getColor(android.R.color.holo_green_dark))
                        }

                        2 -> {
                            binding.option2.setBackgroundColor(resources.getColor(android.R.color.holo_green_dark))
                        }

                        3 -> {
                            binding.option3.setBackgroundColor(resources.getColor(android.R.color.holo_green_dark))
                        }

                        4 -> {
                            binding.option4.setBackgroundColor(resources.getColor(android.R.color.holo_green_dark))
                        }
                    }
                }

                fun wrongAnswerMode(answer: Int) {
                    when (answer) {
                        1 -> {
                            binding.option1.setBackgroundColor(resources.getColor(android.R.color.holo_red_dark))
                        }

                        2 -> {
                            binding.option2.setBackgroundColor(resources.getColor(android.R.color.holo_red_dark))
                        }

                        3 -> {
                            binding.option3.setBackgroundColor(resources.getColor(android.R.color.holo_red_dark))
                        }

                        4 -> {
                            binding.option4.setBackgroundColor(resources.getColor(android.R.color.holo_red_dark))
                        }
                    }

                }

                binding.submit.setOnClickListener() {
                    if (selected == 0) {
                        qOn++
                        when {
                            qOn <= qList!!.size -> {
                                setQuestion(qOn)
                            }

                            else -> {
                                val intent = Intent(this@QuizInterface, ResultPage::class.java)
                                intent.putExtra(ListsOfQuestion.user_name,User_name)
                                intent.putExtra(ListsOfQuestion.correct_answer,score.toString())
                                intent.putExtra(ListsOfQuestion.total_q,qOn-1)
                                startActivity(intent)
                            }
                        }
                    } else {
                        if(selected!=correctA) {
                            wrongAnswerMode(selected)
                            correctAnswerMode(correctA)
                            if(qOn==qList!!.size){binding.submit.text="END QUIZ"}
                            else{binding.submit.text="NEXT"}

                            selected=0
                        }
                        else{correctAnswerMode(selected)
                            correctAnswerMode(correctA)
                            score++
                            if(qOn==qList!!.size){binding.submit.text="END QUIZ"}
                            else{binding.submit.text="NEXT"}

                            selected=0
                        }

                    }
                }

            }

            override fun onFailure(p0: Call<DataFromApi?>, p1: Throwable) {
                Toast.makeText(this@QuizInterface,"${p1.message}",Toast.LENGTH_LONG).show()
                progressDialog.dismiss()
            }
        })


}

      /*  val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://opentdb.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuestionsInterface::class.java)

        var retrofitData = retrofitBuilder.getData()
        retrofitData.enqueue(object : Callback<DataFromApi?> {

            override fun onResponse(p0: Call<DataFromApi?>, p1: Response<DataFromApi?>) {

                var question: Question? = qList?.get(qOn - 1)
                var progressBar = findViewById<ProgressBar>(R.id.progressBar)
                var progressText = findViewById<TextView>(R.id.textProgress)
                var qText = findViewById<TextView>(R.id.q_text)
                var difficulty = findViewById<TextView>(R.id.difficulty_text)
                var category = findViewById<TextView>(R.id.category_text)
                var opt2 = findViewById<TextView>(R.id.option2)
                var opt3 = findViewById<TextView>(R.id.option3)
                var opt4 = findViewById<TextView>(R.id.option4)
                var opt1 = findViewById<TextView>(R.id.option1)
                var submit = findViewById<Button>(R.id.submit)















            }

            override fun onFailure(p0: Call<DataFromApi?>, p1: Throwable) {
                Log.d("Main Activity", "onFailure: "+ p1.message)
            }
        })


        //Log.d("Number of questions", "${qList.size}")















    }

    //1234*/


    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }


}

