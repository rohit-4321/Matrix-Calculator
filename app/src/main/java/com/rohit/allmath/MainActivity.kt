package com.rohit.allmath


import android.content.ContentValues.TAG
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rohit.allmath.ui.calculatorscreen.CalculatorViewModel
import com.rohit.allmath.ui.calculatorscreen.Home
import com.rohit.allmath.ui.navigationscreen.matrix.DeterminantViewModel
import com.rohit.allmath.ui.navigationscreen.matrix.editText
import com.rohit.allmath.ui.navigationscreen.matrix.matrixScreen
import com.rohit.allmath.ui.theme.AllMathTheme

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
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                   // Home(calculatorViewModel)
                    matrixScreen(determinantViewModel)
                   // forTest()

                }
            }
        }
    }
}



