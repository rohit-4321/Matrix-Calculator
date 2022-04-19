package com.rohit.allmath.ui.matrixoperationscreens.matrix


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rohit.allmath.ui.component.MatrixCells
import com.rohit.allmath.ui.component.NumberPicker

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalFoundationApi
@Composable
fun MatrixScreen(viewModel : DeterminantViewModel = viewModel())
{
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp

    val count = viewModel.count.observeAsState(initial = 2)
    val result = viewModel.result.observeAsState()
    viewModel.setMatrixList()

    Box(modifier = Modifier
        .fillMaxSize()
    )
    {
        Column {
            NumberPicker(count.value,12,
                onIncrement =
                {viewModel.increaseMatrixSize()} ,
                onDecrement =
                {viewModel.decreaseMatrixSize()}
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
                    screenWidth =  screenWidth,
                    changeValue = { value , position ->
                        viewModel.addValue(
                            if(value == ""){"0"}else value,
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
            if(result.value != null)
            {
                Text(text = "Result: ", fontSize = 32.sp , modifier =  Modifier.padding(8.dp))
                Text(text = result.value.toString(),
                    fontSize = 22.sp,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }


}


@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalFoundationApi
@Composable
@Preview
fun PreviewMatrixScreen(){
    MatrixScreen(viewModel())
}
