package com.human_developing_soft.productcalc.calculator_keyboard

/**
 * Human Developing Soft
 * @author Egor Ponomarev
 */
interface KeyActionListener {
    fun onKeyPressed(keyAction: CalculationAction, index: Int)
}