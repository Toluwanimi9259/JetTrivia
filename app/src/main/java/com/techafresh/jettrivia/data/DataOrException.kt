package com.techafresh.jettrivia.data

import com.techafresh.jettrivia.model.QuestionItem

data class DataOrException<T, Boolean, E:Exception>(
    var data: T? = null,
    var loading: Boolean? = null,
    var e: E? =  null
)