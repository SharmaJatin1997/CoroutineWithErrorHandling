package com.example.coroutineswitherrorhandling.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coroutineswitherrorhandling.data.api.ApiHelper
import com.example.coroutineswitherrorhandling.learn.retrofit.ExceptionHandler.ExceptionHandlerViewModel
import com.example.coroutineswitherrorhandling.learn.retrofit.ignore_error_continue.IgnoreErrorAndContinueViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ExceptionHandlerViewModel::class.java)) {
            return ExceptionHandlerViewModel(apiHelper) as T
        }
        if (modelClass.isAssignableFrom(IgnoreErrorAndContinueViewModel::class.java)) {
            return IgnoreErrorAndContinueViewModel(apiHelper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}