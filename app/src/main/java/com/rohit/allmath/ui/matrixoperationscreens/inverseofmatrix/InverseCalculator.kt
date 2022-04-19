package com.rohit.allmath.ui.matrixoperationscreens.inverseofmatrix



fun inverseOfMatrix(arr : MutableList<MutableList<Double>>): MutableList<MutableList<Double>>? {

    var a = getIdentityMatrix(arr.size)
    for(i in 0 until arr.size)
    {
        // If element is zero than swap the row.
        if(arr[i][i] == 0.0){
            for(r in i+1 until arr.size)
            {
                if(arr[r][i] !=0.0)
                {
                    for(x in 0 until arr.size)
                    {
                        arr[r][x]= arr[i][x].also { arr[i][x] = arr[r][x] }
                        a[r][x]= a[i][x].also { a[i][x] = a[r][x] }

                    }
                    break
                }
                if(r == arr.size -1 ) return null
            }

        }
        // Make element equal to one.
        if(arr[i][i] != 1.0)
        {
            divideRow(arr , arr[i][i], i,a)
        }
        for(j in 0 until arr.size)
        {
            if(i == j || arr[j][i] == 0.0)
            {
                continue
            }else
            {
                rowOperation(arr , arr[j][i] , i , j,a)
            }

        }
    }

    return a
}

fun divideRow(arr: MutableList<MutableList<Double>>, num: Double, i: Int , a : MutableList<MutableList<Double>>) {

    for(k in 0 until arr.size)
    {
        arr[i][k] = arr[i][k]/ num
        a[i][k] = a[i][k]/ num
    }
}

fun rowOperation(arr : MutableList<MutableList<Double>>,
                 num : Double ,
                 i : Int,
                 j : Int,
                 a : MutableList<MutableList<Double>>)
{

    for(k in 0 until arr.size)
    {
        arr[j][k] -= arr[i][k]*num
        a[j][k] -= a[i][k]*num
    }
}

fun getIdentityMatrix(size: Int): MutableList<MutableList<Double>> {
    return MutableList(size) { i ->
        MutableList(size) { j ->
            if (i == j) 1.0 else 0.0
        }
    }
}