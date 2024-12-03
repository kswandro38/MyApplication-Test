package com.kalanasarange.myapplication.data.repository

import com.kalanasarange.myapplication.api.MyAPI
import com.kalanasarange.myapplication.api.ResponseState
import com.kalanasarange.myapplication.data.model.UserResponse
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This class acts as a single source of truth for user data and
 * provides methods to fetch users from the API. It handles network
 * requests and potential exceptions, returning a ResponseState
 * object to indicate the outcome of the operation.
 */
@Singleton
class UserRepository @Inject constructor(
    private val myAPI: MyAPI
): Repository() {

    /**
     * Retrieves a list of users from the API.
     *
     * This function makes a network request to fetch user data and
     * returns a ResponseState object to indicate success or failure.
     * In case of an error, the exception is handled and a corresponding
     * error state is returned.
     *
     * @return ResponseState<UserResponse> - A ResponseState object containing
     * either a list of users in a ResponseState.Success state or an error message
     * in a ResponseState.
     */
    suspend fun getUsers(): ResponseState<UserResponse> {
        return try{
            ResponseState.Success(myAPI.getUsers())
        } catch (exception: Exception){
            handleException(exception)
        }
    }
}