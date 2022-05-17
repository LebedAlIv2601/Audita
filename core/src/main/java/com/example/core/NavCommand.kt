package com.example.core

import android.os.Bundle
import androidx.navigation.NavOptions

data class NavCommand(
    val action: Int,
    var args: Bundle? = null,
    val navOptions: NavOptions? = null
)
