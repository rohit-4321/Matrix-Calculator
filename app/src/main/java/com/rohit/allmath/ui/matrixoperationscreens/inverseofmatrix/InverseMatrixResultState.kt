package com.rohit.allmath.ui.matrixoperationscreens.inverseofmatrix

sealed class InverseMatrixResultState {
    class Success(var matrix : MutableList<MutableList<Double>>?) : InverseMatrixResultState()
    object DeterminantZero : InverseMatrixResultState()
    object EmptyCells : InverseMatrixResultState()
}