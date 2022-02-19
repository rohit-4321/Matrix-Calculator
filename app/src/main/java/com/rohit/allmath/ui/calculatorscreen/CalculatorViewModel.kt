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
    private val _expression  = MutableLiveData("")
    val expression : LiveData<String> = _expression

    private val _result = Transformations.map(_expression){
        calculateExpression(it)
    }
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
        Log.i(TAG, "equalButtonClicked: Button Clicked............")
    }

    fun clearOneDigit() = _expression.postValue(_expression.value?.dropLast(1))

    fun clearAll() = _expression.postValue("")


}