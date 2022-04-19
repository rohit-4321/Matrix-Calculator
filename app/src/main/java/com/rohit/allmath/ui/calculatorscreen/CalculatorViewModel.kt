package com.rohit.allmath.ui.calculatorscreen

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.core.widget.ListViewAutoScrollHelper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.rohit.allmath.R
import org.mariuszgromada.math.mxparser.Expression

class CalculatorViewModel : ViewModel() {

    // For taking input from the user...
    private val _expression  = MutableLiveData("0")
    val expression : LiveData<String> = _expression

    // calculating result every time after user change in expression.....
    private val _result = Transformations.map(_expression){
        calculateExpression(it)
    }


    private val _expressionIsFocused = MutableLiveData<Boolean>(true)
    val expressionIsFocused : LiveData<Boolean> = _expressionIsFocused

    private val _resultIsOnFocused = MutableLiveData(false)
    val resultIsOnFocused : LiveData<Boolean> = _resultIsOnFocused



    val result :LiveData<String> = _result


    private fun calculateExpression(expression : String) : String{
        val exp = Expression(expression)

        return exp.calculate().toString()
    }


    fun addExpression(value : String) {
        _expression.postValue(_expression.value + value)
        Log.i(TAG, "addExpression: " + _expression.value)

    }


    fun equalButtonClicked()
    {
        _expressionIsFocused.postValue(false)
        _resultIsOnFocused.postValue(true)
        Log.i(TAG, "equalButtonClicked: Button Clicked............")
    }

    fun clearOneDigit() = _expression.postValue(_expression.value?.dropLast(1))

    fun clearAll() = _expression.postValue("")


}