package com.rohit.allmath.ui.matrixoperationscreens.matrix

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class DeterminantCalculatorKtTest{

    @ParameterizedTest
    @MethodSource("getData")
    fun checkDeterminant( matrix : MutableList<MutableList<String>> , ans : String)
    {
        assertEquals(ans , det(matrix))
    }

    companion object {
        @JvmStatic
        fun getData(): Stream<Arguments>? {
            return Stream.of(
                Arguments.of(
                    mutableListOf(
                        mutableListOf("2","3","1"),
                        mutableListOf("-1","2","3"),
                        mutableListOf("3","2","-1"),
                    ) , "0.0"
                ),
                Arguments.of(
                    mutableListOf(
                        mutableListOf("1","-6", "5"),
                        mutableListOf("2","2","5"),
                        mutableListOf("-1","-4","1")
                    ), "34.0"
                ),
                Arguments.of(
                    mutableListOf(
                        mutableListOf("0","3","2"),
                        mutableListOf("6","7","8"),
                        mutableListOf("1","11","32"),
                    ) , "-434.0"
                )
            )
        }
    }

}