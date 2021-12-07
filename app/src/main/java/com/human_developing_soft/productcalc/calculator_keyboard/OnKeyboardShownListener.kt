package com.human_developing_soft.productcalc.calculator_keyboard

import android.widget.EditText

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
interface OnKeyboardShownListener {
    fun onShow(target: EditText)

    fun onHide(target: EditText)
}