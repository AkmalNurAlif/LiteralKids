package com.example.literalkids

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.literalkids.navigation.Screen
import com.example.literalkids.ui.LoginUI
import com.example.literalkids.ui.RegisterUI
import com.example.literalkids.ui.UIsubcription
import com.example.literalkids.ui.HomepageUI
import com.example.literalkids.ui.OnBoarding1UI
import com.example.literalkids.ui.OnBoarding2UI
import com.example.literalkids.ui.OnBoarding3UI
import com.example.literalkids.ui.OnBoarding4UI
import com.example.literalkids.ui.OnBoarding5UI
import com.example.literalkids.ui.OnBoarding6UI


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainNavigation()
        }
    }
}

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {

        // Halaman Login
        composable(Screen.Login.route) {
            LoginUI(navController = navController)
        }

        // Halaman Register
        composable(Screen.Register.route) {
            RegisterUI(navController = navController)
        }

        // Halaman Subscription
        composable(Screen.Subscription.route) {
            UIsubcription(navController = navController)
        }

        // Halaman Homepage
        composable(Screen.Homepage.route) {
            HomepageUI()
        }

        // Halaman OnBoarding1
        composable(Screen.OnBoarding1.route) {
            OnBoarding1UI(navController = navController)
        }

        // Halaman OnBoarding2
        composable(Screen.OnBoarding2.route) {
            OnBoarding2UI(navController = navController)
        }

        // Halaman OnBoarding3
        composable(Screen.OnBoarding3.route) {
            OnBoarding3UI(navController = navController)
        }

        // Halaman OnBoarding4
        composable(Screen.OnBoarding4.route) {
            OnBoarding4UI(navController = navController)
        }

        // Halaman OnBoarding5
        composable(Screen.OnBoarding5.route) {
            OnBoarding5UI(navController = navController)
        }

        // Halaman OnBoarding6
        composable(Screen.OnBoarding6.route) {
            OnBoarding6UI(navController = navController)
        }

    }
}
