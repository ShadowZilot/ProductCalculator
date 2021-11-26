package com.human_developing_soft.productcalc.add_editing

import com.human_developing_soft.productcalc.calculator_keyboard.CalculationAction

/**
 * Human Developing Soft
 * @author Egor Ponomarev
 */
interface KeyActionListener {
    fun onKeyPressed(keyAction: CalculationAction)
}