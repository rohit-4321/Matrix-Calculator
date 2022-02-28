package com.rohit.allmath.ui.navigation

sealed class NavigationDestination(val route : String)
{
    object Home : NavigationDestination(route = "home")
    object DeterminantOfMatrix : NavigationDestination(route = "determinant_screen")
}
