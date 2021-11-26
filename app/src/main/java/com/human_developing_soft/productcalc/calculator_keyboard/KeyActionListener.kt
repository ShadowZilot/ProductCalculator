package com.human_developing_soft.productcalc.calculator_keyboard

import com.human_developing_soft.productcalc.calculator_keyboard.CalculationAction

/**
 * Human Developing Soft
 * @author Egor Ponomarev
 */
interface KeyActionListener {
    fun onKeyPressed(keyAction: CalculationAction)
}