package com.example.literalkids.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Homepage : Screen("homepage")
    object Subscription : Screen("Subscription")
}