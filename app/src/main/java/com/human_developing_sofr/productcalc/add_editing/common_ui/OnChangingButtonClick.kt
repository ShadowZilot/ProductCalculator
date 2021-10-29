package com.human_developing_sofr.productcalc.add_editing.common_ui

interface OnChangingButtonClick {
    fun onClick(aeConfig: Int)

    object AddEditConfig {
        private const val mAddConfig = 0
        private const val mEditConfig = 1
        private const val mDeleteConfig = 2

        fun valueEConfig() = mEditConfig

        fun valueAConfig() = mAddConfig

        fun valueDConfig() = mDeleteConfig
    }
}