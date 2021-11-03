package com.human_developing_soft.productcalc.product_storage.ui

import com.human_developing_soft.productcalc.R
import com.human_developing_soft.productcalc.databinding.ProductsBudgetInfoBinding
import com.human_developing_soft.productcalc.product_storage.StringContext

interface MoneyInfoView : DayUi.Mapper<Unit> {

    fun changeVisibility(visibility: Int)

    class Base(
        private val mBinding: ProductsBudgetInfoBinding,
        listener: OnDayEditing
    ) : MoneyInfoView {

        init {
            mBinding.allMoneyEdit.setOnClickListener {
                listener.onDayEdit()
            }
        }

        override fun changeVisibility(visibility: Int) {
            mBinding.root.visibility = visibility
        }

        override fun map(id: Int, money: Int, moneyRest: Int, productSumma: Int) {
            mBinding.apply {
                val strings = StringContext.Base(this.root.context)
                this.allMoneyView.text = strings.string(R.string.all_money, money)
                this.wastedMoneyView.text = strings.string(R.string.wasted_money,
                    productSumma)
                this.restMoneyView.text = strings.string(R.string.rest_money, moneyRest)
            }
        }
    }
}