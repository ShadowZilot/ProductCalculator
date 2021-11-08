package com.human_developing_soft.productcalc

import android.util.Log

interface GlobalLog {
    fun i(message: String)

    fun d(message: String)

    fun w(message: String)

    fun v(message: String)

    fun e(message: String)

    class Base(
        private val mTag: String
    ) : GlobalLog {

        constructor(instanceType: Class<out Any>): this(instanceType.simpleName)

        override fun i(message: String) {
            Log.i(mTag, message)
        }

        override fun d(message: String) {
            Log.d(mTag, message)
        }

        override fun w(message: String) {
            Log.w(mTag, message)
        }

        override fun v(message: String) {
            Log.v(mTag, message)
        }

        override fun e(message: String) {
            Log.e(mTag, message)
        }
    }
}