package com.rohit.allmath.ui.matrixoperationscreens.inverseofmatrix

import android.content.ContentValues.TAG
import android.graphics.Point
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rohit.allmath.ui.matrixoperationscreens.matrix.det

class InverseViewModel : ViewModel() {

    private val matrixSize = 2
    private val _resultVisible = MutableLiveData(false)
    val resultVisible : LiveData<Boolean>
        get() = _resultVisible

    private var _result = MutableLiveData<InverseMatrixResultState?>(null)
    val result : LiveData<InverseMatrixResultState?>
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

        if(det(matrixItems) != "0.0")
        {
            if(matrixContainEmptyString(matrixItems))
            {
                _result.postValue(InverseMatrixResultState.EmptyCells)


            }else
            _result.postValue(InverseMatrixResultState.Success(inverseOfMatrix(stringToDoubleMatrix(matrixItems)))) // convert matrix item array string into double.
        }else
        {
            _result.postValue(InverseMatrixResultState.DeterminantZero)
        }
        _resultVisible.postValue(true)
    }

    fun stringToDoubleMatrix(arr : MutableList<MutableList<String>>) : MutableList<MutableList<Double>>
    {
        var doubleArr = mutableListOf<MutableList<Double>>()

        for(list in arr)
        {
            var temp = mutableListOf<Double>()
            for(number in list)
            {
                temp.add(number.toDouble())
            }
            doubleArr.add(temp)
        }
        Log.i(TAG, "stringToDoubleMatrix: $doubleArr")
        return doubleArr
    }
    fun matrixContainEmptyString(arr : MutableList<MutableList<String>>) : Boolean
    {
        for(list in arr)
            for(number in list)
                if(number == "") return true;

        return false;
    }


}