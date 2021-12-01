package com.human_developing_soft.productcalc.add_editing.products.ui

/**
 * Human Developing Soft
 *
 * Classes which implement this interface
 * must can calculate summary price or get user inputted price
 *
 * @author Egor Ponomarev
 */
interface SummaryPrice {
    fun price(): Float

    fun clearedNote(): String

    class Base(
        private val mPriceForWeight: Float,
        private val mWeight: Float,
        private val mNote: String
    ) : SummaryPrice {
        override fun price(): Float {
            return if (mNote.contains("/|/")) {
                val builder = StringBuilder()
                for (char in mNote.reversed()) {
                    if (char == '/') {
                        break
                    } else {
                        builder.append(char)
                    }
                }
                if (builder.isEmpty()) 0f
                else builder.toString().reversed().toFloat()
            } else {
                mPriceForWeight * mWeight
            }
        }

        override fun clearedNote(): String {
            return if (mNote.contains("/|/")) {
                val builder = StringBuilder(mNote)
                for (char in builder) {
                    if (char == '|') {
                        builder.deleteRange(
                            builder.indexOf(char)-1,
                            builder.length
                        )
                    }
                }
                builder.toString()
            } else {
                mNote
            }
        }
    }
}