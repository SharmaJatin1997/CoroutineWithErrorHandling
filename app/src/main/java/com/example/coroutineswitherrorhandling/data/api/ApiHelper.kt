package com.example.coroutineswitherrorhandling.data.api

import com.example.coroutineswitherrorhandling.data.model.ApiUser

interface ApiHelper {

    suspend fun getUsers(): List<ApiUser>

    suspend fun getMoreUsers(): List<ApiUser>

    suspend fun getUsersWithError(): List<ApiUser>

}