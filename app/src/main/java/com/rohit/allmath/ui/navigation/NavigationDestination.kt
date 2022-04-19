package com.rohit.allmath.ui.navigation

sealed class NavigationDestination(val route : String)
{
    object Home : NavigationDestination(route = "home")
    object DeterminantOfMatrix : NavigationDestination(route = "determinant_screen")
    object InverseMatrixScreen : NavigationDestination(route = "inverse_of_matrix_screen")
}
