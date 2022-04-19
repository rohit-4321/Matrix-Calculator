package com.rohit.allmath.ui.calculatorscreen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rohit.allmath.R
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


@ExperimentalFoundationApi
@Composable
fun CalculatorScreen(calculatorViewModel:CalculatorViewModel = viewModel()) {
    Column(modifier = Modifier.fillMaxSize()) {
        ResultArea(calculatorViewModel)
        buttonArea(calculatorViewModel)
    }
}

@ExperimentalFoundationApi
@Composable
fun ResultArea(calculatorViewModel:CalculatorViewModel)
{
    val textStyleForFocused =  TextStyle(
        fontSize = 35.sp,
        color = Color.White
    )
    val textStyleForNonFocused = TextStyle(
        fontSize = 22.sp,
        color = Color.Gray
    )
    val resultIsFocused = calculatorViewModel.resultIsOnFocused.observeAsState()
    val expressionIsOnFocused = calculatorViewModel.expressionIsFocused.observeAsState()
    val expression : String by calculatorViewModel.expression.observeAsState("ty")
    val result : String by calculatorViewModel.result.observeAsState("")
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(380.dp)
        .padding(9.dp)) {
        Column(modifier = Modifier.align(Alignment.BottomEnd), horizontalAlignment = Alignment.End) {
            Text(text =expression,
                style = if(expressionIsOnFocused.value == true) textStyleForFocused
                else textStyleForNonFocused )
            Text(text = result ,
                style = if(resultIsFocused.value == true) textStyleForFocused
                else textStyleForNonFocused )
        }

    }
}

@ExperimentalFoundationApi
@Composable
fun buttonArea(calculatorViewModel: CalculatorViewModel)
{
    val keyBoardList : MutableList<MutableList<ButtonType>> = mutableListOf(
        mutableListOf(SymbolType(R.drawable.clearall,null),
            SymbolType(R.drawable.clear,null),
            SymbolType(R.drawable.percent,"%"),
            SymbolType(R.drawable.divide,"/")
        ),
        mutableListOf(NumberType("7"),
            NumberType("8"),
            NumberType("9"),
            SymbolType(R.drawable.multiply,"*")
        ),
        mutableListOf(NumberType("4"),
            NumberType("5"),
            NumberType("6"),
            SymbolType(R.drawable.minus,"-")
        ),
        mutableListOf(NumberType("1"),
            NumberType("2"),
            NumberType("3"),
            SymbolType(R.drawable.plus,"+")

        ),
        mutableListOf(SymbolType(R.drawable.clearall,null),
            NumberType("0"),
            SymbolType(R.drawable.dot , "."),
            SymbolType(R.drawable.equal , null)
        )
    )

    ButtonColumn(list = keyBoardList, calculatorViewModel = calculatorViewModel)

}


@Composable
fun ButtonColumn(list : MutableList<MutableList<ButtonType>> , calculatorViewModel: CalculatorViewModel){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(9.dp) ) {
        list.forEach{
            ButtonRow(
                list = it,
                calculatorViewModel = calculatorViewModel,
                Modifier.weight(1f)
            )
        }

    }

}

@Composable
fun ButtonRow(list : MutableList<ButtonType> , calculatorViewModel: CalculatorViewModel , modifier : Modifier)
{
    Row(modifier = modifier,
    horizontalArrangement = Arrangement.SpaceBetween) {
        list.forEach{
            InputButtons(
                buttonType = it,
                modifier = Modifier
                    .padding(2.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .weight(1f)
                    .background(Color(0xff1a1a1a))
                    .fillMaxHeight()
                    .clickable {
                        it.onButtonClick(calculatorViewModel)
                    }
            )
        }
    }
}

@Composable
fun InputButtons(buttonType: ButtonType, modifier: Modifier)
{
    Box(modifier = modifier
    ){
        if(buttonType is NumberType)
        {
            Text(modifier = Modifier
                .align(Alignment.Center), fontSize = 30.sp,text = buttonType.symbol!!)

        }else if(buttonType is SymbolType)
        {
            Image(modifier = Modifier
                .align(Alignment.Center)
                .size(20.dp),
                colorFilter = ColorFilter.tint(Color(0xffECB365)),
                painter = painterResource(id = buttonType.id!!),
                contentDescription = null )
        }

    }
}
//@ExperimentalFoundationApi
//@Preview(showBackground = true)
//@Composable
//fun Cal() {
//    AllMathTheme {
//        CalculatorScreen(CalculatorViewModel())
//    }
//}
