package com.andreisingeleytsev.listeningmeditationapp.ui.navigation


import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.andreisingeleytsev.listeningmeditationapp.common.Constants
import com.andreisingeleytsev.listeningmeditationapp.ui.screens.home_screen.HomeScreen
import com.andreisingeleytsev.listeningmeditationapp.ui.screens.onboard_screen.OnBoardScreen
import com.andreisingeleytsev.listeningmeditationapp.ui.screens.player_screen.PlayerScreen
import com.andreisingeleytsev.listeningmeditationapp.ui.screens.tip_screen.TipScreen
import com.andreisingeleytsev.listeningmeditationapp.ui.utils.Routes


@Composable
fun ListeningMeditationAppMainNavigationGraph(
    startDestination: String
) {
    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController, startDestination = startDestination,
        modifier = Modifier.background(Color.Transparent)
    ) {
        composable(Routes.ONBOARDING_SCREEN) {
            OnBoardScreen()
        }

        composable(Routes.HOME_SCREEN){
            HomeScreen(navHostController = navHostController)
        }

        composable(Routes.TIP_SCREEN){
            TipScreen(navHostController)
        }
        composable(Routes.PLAYER_SCREEN+"/{${Constants.INDEX_KEY}}"+"/{${Constants.ITEM_INDEX_KEY}}"){
            PlayerScreen(navHostController = navHostController)
        }
    }
}
