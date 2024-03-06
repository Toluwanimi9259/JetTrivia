package com.techafresh.jettrivia.repo

import android.util.Log
import com.techafresh.jettrivia.data.DataOrException
import com.techafresh.jettrivia.model.Question
import com.techafresh.jettrivia.model.QuestionItem
import com.techafresh.jettrivia.network.QuestionAPI
import javax.inject.Inject

class QuestionRepo @Inject constructor(private val api : QuestionAPI){
    private val listOfQuestions = DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()

    suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception>{
        try {
            listOfQuestions.loading = true
            listOfQuestions.data = api.getAllQuestions()
            if (listOfQuestions.data.toString().isNotEmpty()) listOfQuestions.loading = false
        }catch (ex : Exception){
            listOfQuestions.e = ex
            Log.d("MYTAG", "getAllQuestions: Repo ${listOfQuestions.e!!.localizedMessage}")
        }
        return listOfQuestions
    }
}