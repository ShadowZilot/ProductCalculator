package com.human_developing_soft.productcalc.calculator_keyboard

/**
 * Human Developing Soft
 * @author Egor Ponomarev
 */
interface KeyActionRecognition {
    fun keyAction(selectedIndex: Int): CalculationAction

    class Base(
        private val mKey: String,
        private val mListener: NotValidFormulaListener
    ) : KeyActionRecognition {
        override fun keyAction(selectedIndex: Int): CalculationAction {
            return when {
                "1234567890".contains(mKey) -> Number(mKey, selectedIndex)
                "+-/*".contains(mKey) -> MathOperator(mKey)
                mKey == "C" -> ClearOperation
                mKey == "()" -> Brackets(selectedIndex)
                mKey == "+/-" -> PlusSlashMinus
                mKey == "." -> Point
                mKey == "=" -> EqualOperation(mListener)
                mKey == "Del" -> EraseOne(selectedIndex)
                mKey == "%" -> PercentOperation(selectedIndex)
                else -> throw IllegalStateException("Unrecognized key -> $mKey")
            }
        }
    }
}