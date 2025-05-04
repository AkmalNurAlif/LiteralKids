package com.example.literalkids.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Homepage : Screen("homepage")
    object Subscription : Screen("Subscription")
    object OnBoarding1 : Screen("OnBoarding1")
    object OnBoarding2 : Screen("OnBoarding2")
    object OnBoarding3 : Screen("OnBoarding3")
    object OnBoarding4 : Screen("OnBoarding4")
    object OnBoarding5 : Screen("OnBoarding5")
    object OnBoarding6 : Screen("OnBoarding6")
}