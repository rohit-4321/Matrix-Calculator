package com.rohit.allmath.ui.calculatorscreen


import com.rohit.allmath.R

sealed class ButtonType(val symbol : String? = null , val id : Int? = null){


    class NumberType(symbol :String
    ) : ButtonType(symbol)
    class SymbolType(id : Int,
                     symbol : String? = null
    ) : ButtonType(symbol,id)
    fun onButtonClick(viewModel : CalculatorViewModel){
        if(symbol != null)
        {
            viewModel.addExpression(symbol!!)
        }else{
            when(id){
                R.drawable.clear -> viewModel.clearOneDigit()
                R.drawable.clearall -> viewModel.clearAll()
                R.drawable.equal -> viewModel.equalButtonClicked()
            }
        }
    }
}
