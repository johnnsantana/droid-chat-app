package com.johnnsantana.droidchat.ui.feature.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johnnsantana.droidchat.data.repository.AuthRepository
import com.johnnsantana.droidchat.model.NetworkException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
   private val auhRepository: AuthRepository
) : ViewModel() {

    private val _authenticationState = MutableSharedFlow<AuthenticationState>()
    val authenticationState = _authenticationState.asSharedFlow()

    var showErrorDialogState by mutableStateOf(false)
        private set

    fun checkSession() {
        dismissErrorDialog()
        viewModelScope.launch {
            val accessToken = auhRepository.getAccessToken()

            if (accessToken.isNullOrBlank()) {
                _authenticationState.emit(AuthenticationState.UserNotAuthenticated)
                return@launch
            }

            auhRepository.authenticate(accessToken).fold (
                onSuccess = {
                    _authenticationState.emit(AuthenticationState.UserAuthenticated)
                },
                onFailure = {
                    if (it is NetworkException.ApiException && it.statusCode == 401) {
                        auhRepository.clearAccessToken()
                        _authenticationState.emit(AuthenticationState.UserNotAuthenticated)
                    } else {
                        showErrorDialogState = true


                    }
                }
            )

        }
    }

    fun dismissErrorDialog() {
        showErrorDialogState = false
    }


    sealed interface  AuthenticationState {
        data object UserAuthenticated: AuthenticationState
        data object UserNotAuthenticated: AuthenticationState
    }
}