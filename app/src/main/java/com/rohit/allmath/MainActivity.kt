package com.rohit.allmath

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.*
import com.rohit.allmath.ui.component.DrawMatrix
import com.rohit.allmath.ui.matrixoperationscreens.inverseofmatrix.InverseOfMatrixScreen
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
                  //InverseOfMatrixScreen()
                    //DrawMatrix(modifier = Modifier)
                    MainNav(navController)
                }
            }
        }
    }
}

