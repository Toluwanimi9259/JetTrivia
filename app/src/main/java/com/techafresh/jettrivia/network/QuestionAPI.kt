package com.techafresh.jettrivia.network

import com.techafresh.jettrivia.model.Question
import okhttp3.Response
import retrofit2.http.GET

interface QuestionAPI {
    @GET("world.json")
    suspend fun getAllQuestions() : Question
}