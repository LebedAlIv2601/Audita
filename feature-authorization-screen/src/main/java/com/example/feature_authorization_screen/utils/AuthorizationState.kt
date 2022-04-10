package com.example.feature_authorization_screen.utils

sealed class AuthorizationState<out T>(val data: T?, val message: String?) {

    class Success<T>(data: T?):
        AuthorizationState<T>(data = data, message = null)

    class Error<T>(data: T?, message: String?):
        AuthorizationState<T>(data = data, message = message)

    class Loading<T>(data: T?):
        AuthorizationState<T>(data = data, message = null)

    class Waiting<T>():
        AuthorizationState<T>(data = null, message = null)

}