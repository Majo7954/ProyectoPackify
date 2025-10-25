package com.ucb.deliveryapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.deliveryapp.data.entity.User
import com.ucb.deliveryapp.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = UserRepository(app)

    private val _loginState = MutableStateFlow(false)
    val loginState: StateFlow<Boolean> = _loginState

    fun register(user: User) {
        viewModelScope.launch { repo.registerUser(user) }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val user = repo.login(email, password)
            _loginState.value = user != null
        }
    }
}
