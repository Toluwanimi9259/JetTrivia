package com.techafresh.jettrivia.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techafresh.jettrivia.data.DataOrException
import com.techafresh.jettrivia.model.QuestionItem
import com.techafresh.jettrivia.repo.QuestionRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(private val repo: QuestionRepo) : ViewModel() {
    val listOfQuestions : MutableState<DataOrException<ArrayList<QuestionItem>, Boolean, Exception>> =
        mutableStateOf(DataOrException(null,true,Exception("")))

    init {
        getAllQuestions()
    }

    private fun getAllQuestions(){
        viewModelScope.launch {
            listOfQuestions.value.loading = true
            listOfQuestions.value = repo.getAllQuestions()
            if (listOfQuestions.value.data.toString().isNotEmpty()){
                listOfQuestions.value.loading = false
            }
        }
    }

    fun getTotalQuestionCount(): Int {
        return listOfQuestions.value.data?.toMutableList()?.size!!
    }
}