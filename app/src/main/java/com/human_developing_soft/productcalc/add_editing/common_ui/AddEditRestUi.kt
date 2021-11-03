package com.human_developing_soft.productcalc.add_editing.common_ui

import com.human_developing_soft.productcalc.R
import com.human_developing_soft.productcalc.add_editing.common_ui.OnChangingButtonClick.AddEditConfig
import com.human_developing_soft.productcalc.databinding.AeExpenditureFragmentBinding
import com.human_developing_soft.productcalc.databinding.AeProductsFragmentBinding
import com.human_developing_soft.productcalc.product_storage.StringContext

interface AddEditRestUi {
    fun goToAddingConfig()

    fun goToEditConfig()

    class AExpenditureUi(
        private val mBinding: AeExpenditureFragmentBinding,
        private val mListener: OnChangingButtonClick
    ) : AddEditRestUi {
        private val mString = StringContext.Base(mBinding.root.context)

        override fun goToAddingConfig() {
            mBinding.apply {
                this.aeExpenditureAddButton.text = mString.string(R.string.add_product_label)
                this.aeExpenditureAddButton.setOnClickListener {
                    mListener.onClick(AddEditConfig.valueAConfig())
                }
                this.aeExpenditureToolBar.title = mString.string(R.string.ae_expenditure_title)
                this.aeExpenditureToolBar.menu.getItem(0).isVisible = false
            }
        }

        override fun goToEditConfig() {
            mBinding.apply {
                this.aeExpenditureAddButton.text =
                    mString.string(R.string.edit_button_label)
                this.aeExpenditureAddButton.setOnClickListener {
                    mListener.onClick(AddEditConfig.valueEConfig())
                }
                this.aeExpenditureToolBar.title = mString.string(R.string.editing_tool_label)
                this.aeExpenditureToolBar.menu.getItem(0)
                    .setOnMenuItemClickListener {
                    mListener.onClick(AddEditConfig.valueDConfig())
                    true
                }
            }
        }
    }

    class AEProductUi(
        private val mBinding: AeProductsFragmentBinding,
        private val mListener: OnChangingButtonClick
    ) : AddEditRestUi {
        private val mString = StringContext.Base(mBinding.root.context)

        override fun goToAddingConfig() {
            mBinding.apply {
                this.aeAddButton.text = mString.string(R.string.add_product_label)
                this.aeAddButton.setOnClickListener {
                    mListener.onClick(AddEditConfig.valueAConfig())
                }
                this.aeToolBar.title = mString.string(R.string.ae_title)
                this.aeToolBar.menu.getItem(0).isVisible = false
            }
        }

        override fun goToEditConfig() {
            mBinding.apply {
                this.aeAddButton.text = mString.string(R.string.edit_button_label)
                this.aeAddButton.setOnClickListener {
                    mListener.onClick(AddEditConfig.valueEConfig())
                }
                this.aeToolBar.title = mString.string(R.string.editing_tool_label)
                this.aeToolBar.menu.getItem(0).setOnMenuItemClickListener {
                    mListener.onClick(AddEditConfig.valueDConfig())
                    true
                }
            }
        }
    }
}