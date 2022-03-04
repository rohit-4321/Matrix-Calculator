package com.rohit.allmath

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.*
import com.rohit.allmath.ui.navigation.MainNav
import com.rohit.allmath.ui.theme.AllMathTheme

@ExperimentalPagerApi
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    @RequiresApi(Build.VERSION_CODES.O)
   // @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            AllMathTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface {
                    MainNav(navController)
                }
            }
        }
    }
}

