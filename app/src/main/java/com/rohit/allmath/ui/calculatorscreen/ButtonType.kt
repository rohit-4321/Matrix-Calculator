package com.rohit.allmath.ui.calculatorscreen


import com.rohit.allmath.R

abstract class ButtonType(val symbol : String? = null , val id : Int? = null){


 abstract fun onButtonClick(viewModel: CalculatorViewModel)

}
class NumberType(symbol :String) : ButtonType(symbol)
{
    override fun onButtonClick(viewModel: CalculatorViewModel) {
       viewModel.addExpression(symbol!!)
    }

}
class SymbolType(id : Int,
                 symbol : String? = null
) : ButtonType(symbol,id) {
    override fun onButtonClick(viewModel: CalculatorViewModel) {
        if(symbol != null)
        {
//            when(symbol)
//            {
//                "+" , "-" , "/" ,"*" , "." -> viewModel.addExpression(symbol)
//            }

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
