package com.human_developing_sofr.productcalc.product_storage.ui

import android.widget.TextView
import com.human_developing_sofr.productcalc.R
import com.human_developing_sofr.productcalc.databinding.ProductsBudgetInfoBinding
import com.human_developing_sofr.productcalc.product_storage.StringContext

interface MoneyInfoView : DayUi.Mapper<Unit> {

    class Base(
        private val mAllMoneyView: TextView,
        private val mWastedMoneyView: TextView,
        private val mRestMoneyView: TextView
    ) : MoneyInfoView {

        constructor(binding: ProductsBudgetInfoBinding): this(
            binding.allMoneyView,
            binding.wastedMoneyView,
            binding.restMoneyView
        )

        override fun map(id: Int, money: Int, moneyRest: Int, productSumma: Int) {
            val strings = StringContext.Base(mAllMoneyView.context)
            mAllMoneyView.text = strings.string(R.string.all_money, money)
            mWastedMoneyView.text = strings.string(R.string.wasted_money, productSumma)
            mRestMoneyView.text = strings.string(R.string.rest_money, moneyRest)
        }
    }
}