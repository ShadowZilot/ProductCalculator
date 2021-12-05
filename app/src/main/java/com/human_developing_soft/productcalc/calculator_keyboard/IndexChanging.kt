package com.human_developing_soft.productcalc.calculator_keyboard

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
interface IndexChanging {
    fun index(): Int

    class Base(
        private val mAction: CalculationAction,
        private val mPreviousIndex: Int,
        private val mOldLength: Int,
        private val mNewLength: Int
    ) : IndexChanging {
        override fun index(): Int {
            val index = when(mAction) {
                is Number -> mPreviousIndex+1
                is ClearOperation -> 0
                is EraseOne -> mPreviousIndex-1
                is MathOperator -> mPreviousIndex+1
                is Brackets, Point -> mOldLength + (mNewLength - mOldLength)
                is PercentOperation -> mPreviousIndex+1
                is EqualOperation -> mNewLength
                else -> mPreviousIndex
            }
            return if (index < 0) {
                0
            } else {
                index
            }
        }
    }
}