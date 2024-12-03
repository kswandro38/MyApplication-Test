package com.kalanasarange.myapplication.api

import com.kalanasarange.myapplication.BuildConfig
import com.kalanasarange.myapplication.data.model.UserResponse
import retrofit2.http.GET

/**
 * Represents the API interface for interacting with a remote server to retrieve user data.
 * This interface defines the endpoints and data structures used for communication with the API.
 */
interface MyAPI {
    companion object {
        // The base URL of the API. This value is fetched from the BASE_URL property
        // in the gradle.properties file for security purposes.
        const val BASE_URL = BuildConfig.BASE_URL
    }

    /**
     * Retrieves a list of users from the API.
     *
     * @return [UserResponse] object containing the list of users.
     */
    @GET("users")
    suspend fun getUsers(): UserResponse
}