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


    }
}
