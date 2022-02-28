package com.rohit.allmath.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rohit.allmath.ui.homescreen.HomeScreen
import com.rohit.allmath.ui.matrixoperationscreens.matrix.matrixScreen

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalFoundationApi
@Composable
fun MainNav(navController: NavHostController){
    NavHost(navController = navController, startDestination = NavigationDestination.Home.route)
    {
        composable(NavigationDestination.Home.route)
        {
            HomeScreen(navController)
        }
        composable(NavigationDestination.DeterminantOfMatrix.route)
        {
            matrixScreen()
        }
    }

}