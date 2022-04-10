package com.example.feature_authorization_screen.utils

sealed class Resource<out T>(val data: T?, val message: String?) {

    class Success<T>(data: T?):
        Resource<T>(data = data, message = null)

    class Error<T>(data: T?, message: String?):
        Resource<T>(data = data, message = message)

    class Loading<T>(data: T?):
        Resource<T>(data = data, message = null)

}