package com.kalanasarange.myapplication.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kalanasarange.myapplication.api.ResponseState
import com.kalanasarange.myapplication.data.model.UserResponse
import com.kalanasarange.myapplication.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    // State Flow for handle the data array updates
    private val _userResponse =
        MutableStateFlow<ResponseState<UserResponse>>(ResponseState.Idle())
    val userResponse = _userResponse.asStateFlow()

    /**
     * Load users list as the first task of the view-model
     */
    init {
        loadUsers()
    }

    /**
     * Load the data array with coroutine & fetch the data
     */
    fun loadUsers() {
        viewModelScope.launch {
            with(_userResponse) {
                // Update loading state of the flow
                tryEmit(ResponseState.Loading())
                //Update the success state with data array
                tryEmit(userRepository.getUsers())
            }
        }
    }
}