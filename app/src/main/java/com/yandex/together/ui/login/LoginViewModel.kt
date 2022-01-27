package com.yandex.together.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yandex.together.data.LoginRepository
import com.yandex.together.data.api.LoginResponse
import com.yandex.together.data.api.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    private val _eventsLiveData = MutableLiveData<ResultWrapper<LoginResponse>>()
    val eventsLiveData: LiveData<ResultWrapper<LoginResponse>> = _eventsLiveData

    fun login(login: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _eventsLiveData.postValue(repository.login(login, password))
        }
    }
}