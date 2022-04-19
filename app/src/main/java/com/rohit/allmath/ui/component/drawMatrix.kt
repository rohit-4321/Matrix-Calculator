package com.rohit.allmath.ui.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DrawMatrix(result: MutableList<MutableList<Double>>)
{
    val ls = result

    // I can use OnGlobalPosition Modifier for row height. Read about it.
    Row(modifier = Modifier
        .horizontalScroll(rememberScrollState())
        .height(IntrinsicSize.Min)) {

        Box(modifier = Modifier
            .fillMaxHeight()
            .width(12.dp)) {
            Canvas(modifier = Modifier.fillMaxSize()){
                var canvasWidth = size.width
                var canvasHeight = size.height
                drawLine(
                    start = Offset(x= 0f , y = 0f),
                    end = Offset(x = 0f, y = canvasHeight),
                    color = Color.Yellow,
                    strokeWidth = 15F
                )
                drawLine(
                    start = Offset(x= 0f , y = 0f),
                    end = Offset(x = canvasWidth, y = 0f),
                    color = Color.Yellow,
                    strokeWidth = 15F
                )
                drawLine(
                    start = Offset(x= 0f , y = canvasHeight),
                    end = Offset(x = canvasWidth, y = canvasHeight),
                    color = Color.Yellow,
                    strokeWidth = 15F
                )
            }
            
        }

        for(i in 0 until ls.size)
        {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(4.dp)){
                for(j in 0 until ls.size)
                {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .background(Color.LightGray)
                    )
                    {
                        Text(text= "${ls[j][i]}",
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(8.dp),
                            fontSize = 18.sp
                        )
                    }
                }

            }
        }
        Box(modifier = Modifier
            .fillMaxHeight()
            .width(12.dp)) {
            Canvas(modifier = Modifier.fillMaxSize()){
                var canvasWidth = size.width
                var canvasHeight = size.height
                drawLine(
                    start = Offset(x= 0f , y = 0f),
                    end = Offset(x = canvasWidth, y = 0f),
                    color = Color.Yellow,
                    strokeWidth = 15F
                )
                drawLine(
                    start = Offset(x= canvasWidth , y = 0f),
                    end = Offset(x = canvasWidth, y = canvasHeight),
                    color = Color.Yellow,
                    strokeWidth = 15F
                )
                drawLine(
                    start = Offset(x= 0f , y = canvasHeight),
                    end = Offset(x = canvasWidth, y = canvasHeight),
                    color = Color.Yellow,
                    strokeWidth = 15F
                )
            }

        }
    }

}




//Canvas(modifier = modifier){
//    val canvasWidth = size.width
//    val canvasHeight = size.height
//
//    drawLine(
//        start = Offset(x= 0f , y = 0f),
//        end = Offset(x = 0f, y = canvasHeight),
//        color = Color.Yellow,
//        strokeWidth = 25F
//    )
//    drawContext.canvas.nativeCanvas.apply {
//        drawText()
//    }
//    drawLine(
//        start = Offset(x= canvasHeight , y = 0f),
//        end = Offset(x = canvasWidth, y = canvasHeight),
//        color = Color.Yellow,
//        strokeWidth = 25F
//    )
//
//}

