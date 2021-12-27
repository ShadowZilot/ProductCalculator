package com.human_developing_soft.productcalc.smart_fill.same_names

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
interface StringComparing {
    fun comparedSame(otherString: String): Float

    class Base(
        private val mString: String
    ) : StringComparing {
        /**
         * This method compare two string
         * and determine degree of same this string
         *
         * @param otherString comparable string
         * @return decimal number between 0 to 1
         * @author Egor Ponomarev
         */
        override fun comparedSame(otherString: String): Float {
            val max = if (mString.length > otherString.length)
                mString.length else otherString.length
            var amountEqualsChars = 0
            for (i in 0 until max) {
                if (mString[i] == otherString[i]) amountEqualsChars++
            }
            return amountEqualsChars / max.toFloat()
        }
    }
}