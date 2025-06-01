package com.example.literalkids

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.literalkids.navigation.Screen
import com.example.literalkids.ui.ArticleDetailScreen
import com.example.literalkids.ui.AvatarSelectionUI
import com.example.literalkids.ui.BacaCeritaScreenUI
import com.example.literalkids.ui.ChildProfileUI
import com.example.literalkids.ui.GenreSearchUI
import com.example.literalkids.ui.HomeBacaCerita
import com.example.literalkids.ui.LeaderboardUI
import com.example.literalkids.ui.LibraryScreen
import com.example.literalkids.ui.LoginUI
import com.example.literalkids.ui.OnBoarding1UI
import com.example.literalkids.ui.OnBoarding2UI
import com.example.literalkids.ui.OnBoarding3UI
import com.example.literalkids.ui.OnBoarding4UI
import com.example.literalkids.ui.OnBoarding5UI
import com.example.literalkids.ui.OnBoarding6UI
import com.example.literalkids.ui.ParentProfileUI
import com.example.literalkids.ui.ProfileUI
import com.example.literalkids.ui.QuizResultScreen
import com.example.literalkids.ui.QuizScreen
import com.example.literalkids.ui.QuizScreen2
import com.example.literalkids.ui.QuizScreen3
import com.example.literalkids.ui.RegisterUI
import com.example.literalkids.ui.SearchUI
import com.example.literalkids.ui.SubscriptionUI
import com.example.literalkids.ui.HomepageUI
import com.example.literalkids.ui.ParentActivityScreen

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
        startDestination = Screen.ParentActivityScreen.route
    ) {

        // Halaman Login
        composable(Screen.Login.route) {
            LoginUI(navController = navController)
        }

        // Halaman Register
        composable(Screen.Register.route) {
            RegisterUI(navController = navController)
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

        // Halaman Homepage
        composable(Screen.Homepage.route) {
            HomepageUI(navController = navController)
        }

        // Halaman Subscription
        composable(Screen.Subscription.route) {
            SubscriptionUI(navController = navController)
        }

        // Halaman Perpus
        composable(Screen.Perpustakaan.route) {
            LibraryScreen(navController = navController)
        }

        // Halaman Search
        composable(Screen.Search.route) {
            SearchUI(navController = navController)
        }

        // Halaman Genre
        composable(Screen.GenreSelector.route) {
            GenreSearchUI(navController = navController)
        }

        composable(Screen.Profile.route) {
            ProfileUI(navController = navController)
        }

        composable(Screen.ParentProfile.route) {
            ParentProfileUI(navController = navController)
        }

        composable(Screen.ChildProfile.route) {
            ChildProfileUI(navController = navController)
        }

        composable(Screen.AvatarSelection.route) {
            AvatarSelectionUI(navController = navController)
        }

        composable(Screen.Leaderboard.route) {
            LeaderboardUI(navController = navController)
        }

        composable(Screen.HomeBacaCerita.route) {
            HomeBacaCerita(navController = navController)
        }

        composable(Screen.BacaCeritaScreen.route) {
            BacaCeritaScreenUI(navController = navController)
        }

        composable(Screen.Quiz.route) {
            QuizScreen(navController = navController)
        }
        composable(Screen.Quiz2.route) {
            QuizScreen2(navController = navController)
        }
        composable(Screen.Quiz3.route) {
            QuizScreen3(navController = navController)
        }
        composable(Screen.QuizResult.route) {
            QuizResultScreen(navController = navController)
        }
        composable(Screen.ParentActivityScreen.route) {
            ParentActivityScreen(navController = navController)
        }
        composable(Screen.ArticleDetail.route) {
            ArticleDetailScreen()
        }
    }
}