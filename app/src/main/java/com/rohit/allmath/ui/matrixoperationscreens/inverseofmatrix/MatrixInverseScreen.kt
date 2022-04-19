package com.rohit.allmath.ui.matrixoperationscreens.inverseofmatrix

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rohit.allmath.ui.component.DrawMatrix
import com.rohit.allmath.ui.component.MatrixCells
import com.rohit.allmath.ui.component.NumberPicker



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InverseOfMatrixScreen(viewModel : InverseViewModel = viewModel()){

    val context = LocalContext.current



    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp
    
    val count = viewModel.count.observeAsState(initial = 2)
    val result = viewModel.result.observeAsState(initial = null)
    val resultVisibility = viewModel.resultVisible.observeAsState()
    viewModel.setMatrixList()
    Box(modifier = Modifier.fillMaxSize())
    {
        Column() {
            NumberPicker(
                count = count.value,
                onDecrement = { viewModel.decreaseMatrixSize() }, 
                onIncrement = {viewModel.increaseMatrixSize()}
            ) 
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .height(screenHeight * 45 / 100)
                .background(Color.Transparent)){
                MatrixCells(
                    modifier = Modifier
                        .align(Alignment.Center),
                    matrixSize = count.value,
                    screenWidth = screenWidth,
                    changeValue = { value , position ->
                        viewModel.addValue(
                            if(value == "") {""} else value,
                            position = position
                        )
                        
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .background(Color.Transparent)){
                Button(onClick = { viewModel.calculateResult()},
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xffECB365),
                        contentColor = Color.Black),
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(text = "Calculate")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            if(resultVisibility.value == true)
            {
//                if(result.value != null)
//                {
//                    viewModel.result.value?.let { DrawMatrix(it) }
//                }else
//                {
//                    Text(text = "Determinant of Matrix is Zero.")
//                }

                when(result.value)
                {
                    is InverseMatrixResultState.Success -> {
                        (result.value as InverseMatrixResultState.Success).matrix?.let { DrawMatrix(result = it) }
                    }
                    is InverseMatrixResultState.DeterminantZero ->{
                        Text(text = "Determinant of Matrix is Zero.")
                    }
                    is InverseMatrixResultState.EmptyCells ->{
                        Text(text = "Cells Are Empty")
                    }
                }


            }

            
            
        }
        
    }
    
}