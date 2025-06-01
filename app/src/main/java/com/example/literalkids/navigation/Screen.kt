package com.example.literalkids.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Subscription : Screen("Subscription")
    object Homepage : Screen("homepage")
    object Profile : Screen("profile")
    object ParentProfile : Screen("parent_profile")
    object ChildProfile : Screen("child_profile")
    object AvatarSelection : Screen("avatar_selection")
    object Leaderboard : Screen("leaderboard")
    object Perpustakaan : Screen ("perpustakaan")
    object GenreSelector : Screen("genre/{genre}") {
        fun createRoute(genre: String) = "genre/$genre"
    }
    object Search : Screen ("search")
    object Quiz : Screen ("book_quiz1")
    object Quiz2 : Screen ("book_quiz2")
    object Quiz3 : Screen ("book_quiz3")
    object HomeBacaCerita : Screen ("home_baca")
    object BacaCeritaScreen : Screen ("baca_cerita_screen")
    object QuizResult : Screen ("quiz_result")
    object OnBoarding1 : Screen("OnBoarding1")
    object OnBoarding2 : Screen("OnBoarding2")
    object OnBoarding3 : Screen("OnBoarding3")
    object OnBoarding4 : Screen("OnBoarding4")
    object OnBoarding5 : Screen("OnBoarding5")
    object OnBoarding6 : Screen("OnBoarding6")
    object ParentActivityScreen : Screen("parent_activity_screen")
    object ArticleDetail : Screen("article_detail")
}