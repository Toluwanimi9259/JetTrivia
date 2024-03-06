package com.techafresh.jettrivia.di

import com.techafresh.jettrivia.network.QuestionAPI
import com.techafresh.jettrivia.repo.QuestionRepo
import com.techafresh.jettrivia.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideQuestionAPI() : QuestionAPI{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuestionAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRepo(api: QuestionAPI) : QuestionRepo{
        return QuestionRepo(api)
    }
}