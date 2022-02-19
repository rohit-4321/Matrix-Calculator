package com.rohit.allmath.ui.navigationscreen.matrix

import android.content.ContentValues.TAG
import android.graphics.Point
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class DeterminantViewModel : ViewModel() {

    private val matrixSize = 2

    private var _result = MutableLiveData<String>(null)
    val result : LiveData<String>
        get() = _result

    private var matrixItems  = MutableList(matrixSize){ MutableList(matrixSize){"0"}}

    private var _count = MutableLiveData(matrixSize)
    val count : LiveData<Int>
        get() = _count


    fun increaseMatrixSize(){
        if(_count.value!! < 10 )
            _count.postValue(_count.value!! +1)
    }

    fun decreaseMatrixSize(){
        if(_count.value!! > 2)
            _count.postValue(_count.value!! - 1)
    }

    fun setMatrixList() {
        matrixItems  = MutableList(_count.value!!){ MutableList(_count.value!!){"0"}}
    }

    fun addValue(value : String,position : Point){
        matrixItems[position.x][position.y] = value
        Log.i(TAG, "increaseMatrixSize: Mutable List..${matrixItems}")
    }

    fun calculateResult(){
        _result.postValue(det(matrixItems))
    }
}