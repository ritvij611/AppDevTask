package com.example.quizapp


object ListsOfQuestion{

    const val user_name:String= "name"
    const val correct_answer: String="0"
    const val total_q:String="0"


    fun getQ(dataList: List<ResultX>): ArrayList<Question>{
        val qList= ArrayList<Question>()
        val q1 = Question(1,dataList[0].question,dataList[0].category,dataList[0].difficulty,dataList[0].correct_answer,dataList[0].incorrect_answers[1], dataList[0].incorrect_answers[0],dataList[0].incorrect_answers[2],1)
        qList.add(q1)

        val q2 = Question(2,dataList[1].question,dataList[1].category,dataList[1].difficulty,dataList[0].incorrect_answers[1], dataList[0].incorrect_answers[0],dataList[1].correct_answer,dataList[0].incorrect_answers[2],3)
        qList.add(q2)

        val q3 = Question(3,dataList[2].question,dataList[2].category,dataList[2].difficulty,dataList[2].incorrect_answers[2], dataList[2].incorrect_answers[0],dataList[2].incorrect_answers[2],dataList[2].correct_answer,4)
        qList.add(q3)

        val q4 = Question(4,dataList[3].question,dataList[3].category,dataList[3].difficulty,dataList[3].correct_answer,dataList[3].incorrect_answers[1], dataList[3].incorrect_answers[0],dataList[3].incorrect_answers[2],1)
        qList.add(q4)

        val q5 = Question(1,dataList[4].question,dataList[4].category,dataList[4].difficulty,dataList[4].incorrect_answers[1],dataList[4].correct_answer, dataList[4].incorrect_answers[0],dataList[4].incorrect_answers[2],2)
        qList.add(q5)

        val q6 = Question(1,dataList[5].question,dataList[5].category,dataList[5].difficulty,dataList[5].correct_answer,dataList[5].incorrect_answers[1], dataList[5].incorrect_answers[0],dataList[5].incorrect_answers[2],1)
        qList.add(q6)

        val q7 = Question(1,dataList[6].question,dataList[6].category,dataList[6].difficulty,dataList[6].incorrect_answers[1], dataList[6].incorrect_answers[0],dataList[6].incorrect_answers[2],dataList[6].correct_answer,4)
        qList.add(q7)

        val q8 = Question(1,dataList[7].question,dataList[7].category,dataList[7].difficulty,dataList[7].incorrect_answers[1], dataList[7].incorrect_answers[0],dataList[7].correct_answer,dataList[7].incorrect_answers[2],3)
        qList.add(q8)

        val q9 = Question(1,dataList[8].question,dataList[8].category,dataList[8].difficulty,dataList[8].correct_answer,dataList[8].incorrect_answers[1], dataList[8].incorrect_answers[0],dataList[8].incorrect_answers[2],1)
        qList.add(q9)

        val q10 = Question(1,dataList[9].question,dataList[9].category,dataList[9].difficulty,dataList[9].incorrect_answers[1],dataList[9].correct_answer, dataList[9].incorrect_answers[0],dataList[9].incorrect_answers[2],2)
        qList.add(q10)

        return qList
    }
}