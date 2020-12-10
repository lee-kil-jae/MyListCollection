package com.kiljae.mylistcollection.common

import java.text.DecimalFormat

object Utils {

    @JvmStatic
    fun prettyText(value : Int): String{
        val df = DecimalFormat("#,###")
        return df.format(value.toLong()) as String
    }

    @JvmStatic
    fun extactNumbers(str: String): Int{
        var tmpStr = str
        var number = 0
        if (!tmpStr.isNullOrEmpty() && !tmpStr.replace("[^0-9]", "").isNullOrEmpty()) {
            tmpStr = tmpStr.replace("[^0-9]", "")
            if (!tmpStr.isNullOrEmpty()) {
                tmpStr = tmpStr.replace(".", "")
            }
            if (!tmpStr.isNullOrEmpty()) {
                number = Integer.parseInt(tmpStr)
            }
        }
        return number
    }
}