package com.human_developing_soft.productcalc

import android.content.Context
import android.content.SharedPreferences

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
interface SharedPreferencesShell {

    fun valueBoolean(key: String, defValue: Boolean = false): Boolean

    fun valueInt(key: String, defVal: Int = 0): Int

    fun valueFloat(key: String, defVal: Float = 0f): Float

    fun valueString(key: String, defVal: String = ""): String

    fun edit(editing: (editor: SharedPreferences.Editor) -> Unit)

    class Base(
        context: Context,
        key: String,
        mode: Int = 0
    ) : SharedPreferencesShell {
        private val mPreference = context.getSharedPreferences(key, mode)
        override fun valueBoolean(key: String, defValue: Boolean) = mPreference
            .getBoolean(key, defValue)

        override fun valueInt(key: String, defVal: Int) = mPreference
            .getInt(key, defVal)

        override fun valueFloat(key: String, defVal: Float) = mPreference
            .getFloat(key, defVal)

        override fun valueString(key: String, defVal: String) = mPreference
            .getString(key, defVal)!!

        override fun edit(editing: (editor: SharedPreferences.Editor) -> Unit) {
            editing.invoke(mPreference.edit())
        }
    }
}