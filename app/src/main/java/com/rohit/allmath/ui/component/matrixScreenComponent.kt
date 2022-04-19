package com.rohit.allmath.ui.component

import android.graphics.Point
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rohit.allmath.R


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

@ExperimentalFoundationApi
@Composable
fun MatrixCells(changeValue: (String, Point) -> Unit,
                modifier : Modifier,
                matrixSize : Int,
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
                                        EditText(
                                            screenWidth ,
                                            Point(i,j),
                                            changeValue = changeValue
                                        )
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
fun EditText(screenWidth : Int,
             position : Point,
             changeValue : (String, Point) -> Unit
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
            InputtextField(textStyle = textStyle,
                position =position,
                boxWidth =boxWidth,
                modifier = Modifier
                    .width(boxWidth.dp)
                    .border(BorderStroke(1.dp, Color.LightGray))
                    .align(Alignment.Center),
                changeValue = changeValue
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
                subscript : SpanStyle
)
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
fun InputtextField(textStyle : TextStyle,
                   position : Point,
                   boxWidth : Int,
                   modifier : Modifier,
                   changeValue : (String, Point) -> Unit
){

    var value = rememberSaveable{ mutableStateOf("") }

    changeValue(value.value , position)

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

