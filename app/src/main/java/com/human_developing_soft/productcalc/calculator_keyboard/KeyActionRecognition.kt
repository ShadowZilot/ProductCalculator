package com.human_developing_soft.productcalc.calculator_keyboard

/**
 * Human Developing Soft
 * @author Egor Ponomarev
 */
interface KeyActionRecognition {
    fun keyAction(): CalculationAction

    class Base(
        private val mKey: String
    ) : KeyActionRecognition {
        override fun keyAction(): CalculationAction {
            return when {
                "1234567890".contains(mKey) -> Number(mKey)
                "+-/*".contains(mKey) -> MathOperator(mKey)
                mKey == "C" -> ClearOperation
                mKey == "()" -> Brackets
                mKey == "+/-" -> PlusSlashMinus
                mKey == "." -> Point
                mKey == "=" -> EqualOperation
                mKey == "Del" -> EraseOne
                else -> throw IllegalStateException("Unrecognized key -> $mKey")
            }
        }
    }
}