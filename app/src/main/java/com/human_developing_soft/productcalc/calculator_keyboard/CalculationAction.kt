package com.human_developing_soft.productcalc.calculator_keyboard

import java.lang.IndexOutOfBoundsException
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import javax.script.ScriptException

/**
 * Human Developing Soft
 * @author Egor Ponomarev
 */
sealed class CalculationAction {
    abstract fun implementAction(calcString: String): String
}

class Number(
    private val mPressedNumber: String,
    private val mIndex: Int
) : CalculationAction() {

    init {
        if (mPressedNumber.length > 1) {
            throw NumberLengthException(
                "Await length of number = 1, obtain length ${mPressedNumber.length}")
        }
    }

    override fun implementAction(calcString: String): String {
        val result = StringBuilder(calcString)
        return if (calcString.isNotEmpty()
            && calcString.lastSymbol(mIndex) == ")"
        ) {
            result.insert(mIndex, "*${mPressedNumber}")
            result.toString()
        } else {
            result.insert(mIndex, mPressedNumber)
            result.toString()
        }
    }
}

class MathOperator(
    private val mOperator: String
) : CalculationAction() {
    override fun implementAction(calcString: String): String {
        val lastSymbol = calcString.lastSymbol()
        val result = StringBuilder(calcString)
        return if (calcString.isEmpty()
            || lastSymbol == mOperator
        ) {
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
        val lastSymbol = calcString.lastSymbol()
        val result = StringBuilder(calcString)
        return if (isNeedToClose(calcString)) {
            result.append(")")
            result.toString()
        } else {
            if (calcString.isNotEmpty()
                && "0123456789%".contains(lastSymbol)
            ) {
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
        if ("+-*/".contains(calcString.lastSymbol())) {
            result = false
        }
        return result
    }
}

object PlusSlashMinus : CalculationAction() {
    override fun implementAction(calcString: String): String {
        return calcString
    }
}

object Point : CalculationAction() {
    override fun implementAction(calcString: String): String {
        val lastSymbol = calcString.lastSymbol()
        return if (calcString.isEmpty()
            || "(+-*/".contains(lastSymbol)
        ) {
            StringBuilder(calcString)
                .append("0.")
                .toString()
        } else if (")%".contains(lastSymbol)) {
            StringBuilder(calcString)
                .append("*0.")
                .toString()
        } else {
            StringBuilder(calcString)
                .append(".")
                .toString()
        }
    }
}

class EraseOne (
    private var mDeletingIndex: Int
): CalculationAction() {

    init {
        mDeletingIndex -= 1
    }

    override fun implementAction(calcString: String): String {
        return if (calcString.isNotEmpty() && mDeletingIndex >= 0) {
            StringBuilder(calcString)
                .deleteCharAt(mDeletingIndex)
                .toString()
        } else {
            calcString
        }
    }
}

class PercentOperation(
    private val mIndex: Int
) : CalculationAction() {
    override fun implementAction(calcString: String): String {
        val lastSymbol = calcString.lastSymbol(mIndex)
        return if (!"+-/*%".contains(lastSymbol)) {
            StringBuilder(calcString)
                .append("%", mIndex)
                .toString()
        } else {
            calcString
        }
    }
}

class EqualOperation(
    private val mListener: NotValidFormulaListener
) : CalculationAction() {
    override fun implementAction(calcString: String): String {
        val manager = ScriptEngineManager()
        val engine: ScriptEngine = manager.getEngineByName("js")
        return try {
            engine.eval(
                calcString.replace("%", "/100")
            ).toString()
        } catch (e: ScriptException) {
            mListener.onFormulaError()
            calcString
        }
    }
}

fun String.lastSymbol(index : Int = this.length-1): String {
    return if (this.isNotEmpty()) {
        try {
            get(index).toString()
        } catch (e : IndexOutOfBoundsException) {
            get(index-1).toString()
        }
    } else {
        ""
    }
}