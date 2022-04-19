package com.rohit.allmath.ui.matrixoperationscreens.matrix

import org.mariuszgromada.math.mxparser.Expression

fun det(a : MutableList<MutableList<String>>) : String{
    var haveNeg = false;
    var result = "1"

    if(a.size == 2)
    {
        return det2d(a)
    }
    else
    {
        for(i in a.indices){
            var base = a[i][i]
            if(base == "0")
            {
                for(p in i+1 until a.size)
                {
                    if(a[p][i] != "0")
                    {
                        //swap row
                        base = a[p][i]
                        for(c in i until a.size)
                        {
                            a[p][c] = a[i][c].also { a[i][c] = a[p][c] }
                        }
                        haveNeg = !haveNeg

                        break;
                    }
                    if(p == a.size - 1)
                    {
                        return "0.0"
                    }
                }
            }
            for(k in i+1 until a.size)
            {
                if(a[k][i] == "0") continue
                var opr = getSubnum(a[k][i], base)
               // Log.i(TAG, "det: .....................${opr}")
                for(m in i until a.size){
                    a[k][m] = Expression("${a[k][m]}+($opr*${a[i][m]})")
                        .calculate()
                        .toString()
                    //Log.i(TAG, "det: ....................................................${a[k][m]}")

                }
            }

        }

    }
    for(i in a.indices)
    {
        result  = Expression("$result*(${a[i][i]})").calculate().toString()
      //  Log.i(TAG, "Result     $result ")
    }
    return if(haveNeg) "-$result" else result
}

fun getSubnum(num: String, baseNum: String): String {

    var temp = Expression("(0-($num/$baseNum))").calculate().toString()
  //  Log.i(TAG, "getSubnum:  NUM :  $num    baseNUm : $baseNum  temp : $temp")
    return temp
}

fun det2d(arr: MutableList<MutableList<String>>) : String {
    var temp =  Expression("(${arr[0][0]})*(${arr[1][1]}) - (${arr[0][1]})*(${arr[1][0]})").calculate()
    return temp.toString()
}




