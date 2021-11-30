package com.human_developing_soft.productcalc.calculator_keyboard

/**
 * Human Developing Soft
 * @author Egor Ponomarev
 */
interface KeyActionRecognition {
    fun keyAction(): CalculationAction

    class Base(
        private val mKey: String,
        private val mListener: NotValidFormulaListener
    ) : KeyActionRecognition {
        override fun keyAction(): CalculationAction {
            return when {
                "1234567890".contains(mKey) -> Number(mKey)
                "+-/*".contains(mKey) -> MathOperator(mKey)
                mKey == "C" -> ClearOperation
                mKey == "()" -> Brackets
                mKey == "+/-" -> PlusSlashMinus
                mKey == "." -> Point
                mKey == "=" -> EqualOperation(mListener)
                mKey == "Del" -> EraseOne
                mKey == "%" -> PercentOperation
                else -> throw IllegalStateException("Unrecognized key -> $mKey")
            }
        }
    }
}