package com.human_developing_soft.productcalc.calculator_keyboard

import java.lang.StringBuilder

/**
 * Human Developing Soft
 * @author Egor Ponomarev
 */
sealed class CalculationAction {
    abstract fun implementAction(calcString: String): String
}

class Number(
    private val mPressedNumber : String
) : CalculationAction() {
    override fun implementAction(calcString: String): String {
        val result = StringBuilder(calcString)
        return if (calcString.isNotEmpty()
            && calcString[calcString.lastIndex] == ')') {
            result.append("*${mPressedNumber}")
            result.toString()
        } else {
            result.append(mPressedNumber)
            result.toString()
        }
    }
}

class MathOperator(
    private val mOperator: String
) : CalculationAction() {
    override fun implementAction(calcString: String): String {
        val lastSymbol = calcString[calcString.lastIndex].toString()
        val result = StringBuilder(calcString)
        return if (calcString.isEmpty()
            || lastSymbol == mOperator) {
                calcString
        } else if ("+-/*".contains(lastSymbol)) {
            result.deleteCharAt(result.lastIndex)
            result.append(mOperator)
            result.toString()
        } else {
            result.append(mOperator)
            result.toString()
        }
    }
}

object ClearOperation : CalculationAction() {
    override fun implementAction(calcString: String) = ""
}

object Brackets : CalculationAction() {
    override fun implementAction(calcString: String): String {
        val lastSymbol = calcString[calcString.lastIndex].toString()
        val result = StringBuilder(calcString)
        return if (isNeedToClose(calcString)) {
            result.append(")")
            result.toString()
        } else {
            if ("0123456789%".contains(lastSymbol)) {
                result.append("*(")
                result.toString()
            } else {
                result.append("(")
                result.toString()
            }
        }
    }

    private fun isNeedToClose(calcString: String): Boolean {
        var result = false
        for (i in calcString.reversed()) {
            if (i == '(') {
                result = true
                break
            }
        }
        if ("+-*/".contains(calcString[calcString.lastIndex].toString())) {
            result = false
        }
        return result
    }
}

object PlusSlashMinus : CalculationAction() {
    override fun implementAction(calcString: String): String {
        // TODO implements this
        return calcString
    }
}

object Point : CalculationAction() {
    override fun implementAction(calcString: String): String {
        // TODO implement this
        return calcString
    }
}

object EraseOne : CalculationAction() {
    override fun implementAction(calcString: String): String {
        return StringBuilder(calcString)
            .deleteCharAt(calcString.lastIndex)
            .toString()
    }
}

object EqualOperation : CalculationAction() {
    override fun implementAction(calcString: String): String {
        // TODO implement this
        return calcString
    }
}