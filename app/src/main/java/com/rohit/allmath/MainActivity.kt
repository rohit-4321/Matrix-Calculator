package com.rohit.allmath


import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.*
import com.rohit.allmath.ui.homescreen.HomeScreen
import com.rohit.allmath.ui.calculatorscreen.CalculatorScreen
import com.rohit.allmath.ui.calculatorscreen.CalculatorViewModel
import com.rohit.allmath.ui.homescreen.HomeScreenContent
import com.rohit.allmath.ui.homescreen.tabs
import com.rohit.allmath.ui.matrixoperationscreens.matrix.DeterminantViewModel
import com.rohit.allmath.ui.navigation.MainNav
import com.rohit.allmath.ui.theme.AllMathTheme
import kotlinx.coroutines.launch

@ExperimentalPagerApi
class MainActivity : ComponentActivity() {

    private val calculatorViewModel by viewModels<CalculatorViewModel>()
    private val determinantViewModel by viewModels<DeterminantViewModel>()

    @OptIn(ExperimentalFoundationApi::class)
    @RequiresApi(Build.VERSION_CODES.O)
   // @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            AllMathTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainNav(navController)
                   // Home(calculatorViewModel)
                   // matrixScreen(determinantViewModel)
                   // forTest()


                }
            }
        }
    }
}

