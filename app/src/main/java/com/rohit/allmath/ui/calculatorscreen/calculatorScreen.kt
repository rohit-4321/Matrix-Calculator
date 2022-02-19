package com.rohit.allmath.ui.calculatorscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.rohit.allmath.ui.theme.AllMathTheme

@ExperimentalFoundationApi
@Composable
fun Home(calculatorViewModel:CalculatorViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        ResultArea(calculatorViewModel)
        buttonArea(calculatorViewModel)

    }
}

@ExperimentalFoundationApi
@Composable
fun ResultArea(calculatorViewModel:CalculatorViewModel)
{
    val expression : String by calculatorViewModel.expression.observeAsState("ty")
    val result : String by calculatorViewModel.result.observeAsState("")
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(380.dp)
        .padding(9.dp)) {
        Column(modifier = Modifier.align(Alignment.BottomEnd), horizontalAlignment = Alignment.End) {
            Text(fontSize = 30.sp,text =expression, )
            Text(fontSize  = 32.sp , text = result)
        }

    }
}

@ExperimentalFoundationApi
@Composable
fun buttonArea(calculatorViewModel: CalculatorViewModel)
{
    val keyBoardList : MutableList<MutableList<ButtonType>> = mutableListOf(
        mutableListOf(ButtonType.SymbolType(R.drawable.clearall,null),
            ButtonType.SymbolType(R.drawable.clear,null),
            ButtonType.SymbolType(R.drawable.percent,"%"),
            ButtonType.SymbolType(R.drawable.divide,"/")
        ),
        mutableListOf(ButtonType.NumberType("7"),
            ButtonType.NumberType("8"),
            ButtonType.NumberType("9"),
            ButtonType.SymbolType(R.drawable.multiply,"*")
        ),
        mutableListOf(ButtonType.NumberType("4"),
            ButtonType.NumberType("5"),
            ButtonType.NumberType("6"),
            ButtonType.SymbolType(R.drawable.minus,"-")
        ),
        mutableListOf(ButtonType.NumberType("1"),
            ButtonType.NumberType("2"),
            ButtonType.NumberType("3"),
            ButtonType.SymbolType(R.drawable.plus,"+")

        ),
        mutableListOf(ButtonType.SymbolType(R.drawable.clearall,null),
            ButtonType.NumberType("0"),
            ButtonType.SymbolType(R.drawable.dot , "."),
            ButtonType.SymbolType(R.drawable.equal , null)
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
                modifier =  Modifier
                    .padding(2.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .weight(1f)
                    .background(Color(0xFFCBD092))
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
        if(buttonType is ButtonType.NumberType)
        {
            Text(modifier = Modifier
                .align(Alignment.Center), fontSize = 24.sp,text = buttonType.symbol!!)

        }else if(buttonType is ButtonType.SymbolType)
        {
            Image(modifier = Modifier
                .align(Alignment.Center)
                .size(24.dp),
                painter = painterResource(id = buttonType.id!!),
                contentDescription = null )
        }

    }
}
@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun HomePreview() {
    AllMathTheme {
        Home(CalculatorViewModel())
    }
}
