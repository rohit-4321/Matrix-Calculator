package com.rohit.allmath.ui.matrixoperationscreens.matrix

import android.graphics.Point
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rohit.allmath.R

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
                MatrixCells(viewModel,
                    modifier = Modifier
                        .align(Alignment.Center),
                    matrixSize = count.value,
                    screenWidth =  screenWidth
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

@ExperimentalFoundationApi
@Composable
fun MatrixCells(viewModel : DeterminantViewModel,
                modifier : Modifier,
                matrixSize : Int ,
                screenWidth: Int)
{
    Box(modifier = modifier) {
        LazyColumn()
        {
            item{
                LazyRow()
                {
                    item {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            for(i in -1 until matrixSize) {
                                Row(modifier = Modifier,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                    for (j in -1 until matrixSize) {
                                        EditText(viewModel ,screenWidth , Point(i,j))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

}

@Composable
fun EditText(viewModel : DeterminantViewModel,
             screenWidth : Int,
             position : Point
)
{
    val boxWidth  = screenWidth * 13 /100

    var textStyle = TextStyle(
        fontSize = 13.sp,
        textDirection = TextDirection.Content,
        color = if(isSystemInDarkTheme()) Color.White else Color.Black,
        textAlign = TextAlign.Justify
    )
    Box(modifier = Modifier
        .size(boxWidth.dp, 25.dp)) {

        if(position.x == -1 || position.y == -1)
        {
            MatrixCellHeading(position = position,
                Modifier
                    .width(boxWidth.dp)
                    .border(BorderStroke(1.dp, Color.LightGray))
                    .size(boxWidth.dp, 25.dp)
                    .background(if (isSystemInDarkTheme()) Color(0xff3b3c36) else Color.White)
            )

        }else
        {
            InputtextField(viewModel = viewModel,
                textStyle = textStyle,
                position =position,
                boxWidth =boxWidth,
                modifier = Modifier
                    .width(boxWidth.dp)
                    .border(BorderStroke(1.dp, Color.LightGray))
                    .align(Alignment.Center)
            )

        }



    }
}
@Composable
fun MatrixCellHeading(position: Point, modifier : Modifier)
{
    val subscript = SpanStyle(
        baselineShift = BaselineShift.Subscript,
        fontSize = 11.sp,
        color = if(isSystemInDarkTheme()) Color.White else Color.Black
    )

    Box(modifier = modifier
    )
    {
        if(position.x == -1 && position.y == -1)
        {
            HeadingText(
                textModifier = Modifier.align(Alignment.Center),
                text = "X",
                subscriptText = "0",
                subscript = subscript
            )
        }else if(position.x == -1 && position.y > -1 )
        {
            HeadingText(
                textModifier = Modifier.align(Alignment.Center),
                text = "C",
                subscriptText = (position.y+1).toString(),
                subscript = subscript
            )
        }else if(position.y == -1 && position.x > -1 )
        {
            HeadingText(
                textModifier = Modifier.align(Alignment.Center),
                text = "R",
                subscriptText = (position.x+1).toString(),
                subscript = subscript
            )
        }



    }
}
@Composable
fun HeadingText(textModifier : Modifier,
                text : String,
                subscriptText : String,
                subscript : SpanStyle)
{
    Text(
        text =  buildAnnotatedString {
            append(text)
            withStyle(subscript)
            {
                append(subscriptText)
            }
        },
        modifier = textModifier,
        fontSize = 12.sp,
        color = if(isSystemInDarkTheme()) Color.White else Color.Black
    )

}






@Composable
fun InputtextField(viewModel : DeterminantViewModel,
                   textStyle : TextStyle,
                   position : Point,
                   boxWidth : Int,
                   modifier : Modifier
){

    var value = rememberSaveable{ mutableStateOf("")}

    viewModel.addValue(
        if(value.value == ""){"0"}else value.value,
        position = position
    )
    Box(modifier = modifier)
    {
        BasicTextField(value =  value.value ,
            modifier = Modifier
                .requiredWidth(boxWidth.dp)
                .wrapContentWidth(Alignment.End)
                .padding(horizontal = 1.dp)
                .background(if (isSystemInDarkTheme()) Color(0xff3b3c36) else Color.White)
                .height(20.dp)
            ,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            ,
            textStyle = textStyle,
            singleLine = true,
            onValueChange ={
                value.value = it
            }
        )

    }


}



@Composable
fun NumberPicker(count : Int ,
                 contentPadding : Int = 5,
                 onDecrement : () -> Unit ,
                 onIncrement : () -> Unit,)
{
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 9.dp)
    ) {
        Text(modifier = Modifier.padding(horizontal = contentPadding.dp),text = "Size of Matrix : ", fontSize = 25.sp)
        Box(modifier  = Modifier
            .padding(horizontal = contentPadding.dp)
            .size(46.dp)
            .clip(RoundedCornerShape(100.dp))
            .background(Color(0xffECB365))
            .clickable(onClick = onDecrement)) {
            Image(modifier  = Modifier
                .fillMaxSize()
                .padding(13.dp),
                painter = painterResource(id = R.drawable.minus),
                contentDescription = "Decrement Button.")
        }
        Box(modifier  = Modifier
            .width(IntrinsicSize.Min)
            .height(46.dp)
            .clip(RoundedCornerShape(12.dp))) {
            Text(text = count.toString(),
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                modifier = Modifier
                    .align(Alignment.Center))
        }

        Box(modifier  = Modifier
            .padding(horizontal = contentPadding.dp)
            .size(46.dp)
            .clip(RoundedCornerShape(100.dp))
            .background(Color(0xffECB365))
            .clickable(onClick = onIncrement)) {
            Image(modifier  = Modifier
                .fillMaxSize()
                .padding(13.dp),
                painter = painterResource(id = R.drawable.plus),
                contentDescription = "Increment Button.")
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
