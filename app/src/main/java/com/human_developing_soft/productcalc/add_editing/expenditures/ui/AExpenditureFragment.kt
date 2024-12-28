package com.human_developing_soft.productcalc.add_editing.expenditures.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.human_developing_soft.productcalc.add_editing.common_ui.AddEditRestUi
import com.human_developing_soft.productcalc.add_editing.common_ui.OnChangingButtonClick
import com.human_developing_soft.productcalc.add_editing.common_ui.OnChangingButtonClick.AddEditConfig
import com.human_developing_soft.productcalc.add_editing.expenditures.domain.AExpenditureVM
import com.human_developing_soft.productcalc.add_editing.expenditures.domain.AExpenditureVMFactory
import com.human_developing_soft.productcalc.add_editing.products.domain.OnProductUpdatedListener
import com.human_developing_soft.productcalc.databinding.AeExpenditureFragmentBinding
import com.human_developing_soft.productcalc.main.ui.BaseFragment
import com.human_developing_soft.productcalc.navigation.Navigation
import com.human_developing_soft.productcalc.product_storage.ui.ExpenditureUi

class AExpenditureFragment : BaseFragment<AeExpenditureFragmentBinding>(
    bindingInflater = { inflater, container ->
        AeExpenditureFragmentBinding.inflate(inflater, container, false)
    }
), OnExpenditureObserver,
    OnProductUpdatedListener, OnChangingButtonClick {
    private lateinit var mViewModel: AExpenditureVM
    private lateinit var mUi: AExpenditureUi
    private var mId: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mId = arguments?.getInt("id")
        if (mId == -1) mId = null
        mUi = AExpenditureUi.Base(
            ExpendituresFieldImpl(
                binding.aeExpenditureFields,
                mId
            ),
            AddEditRestUi.AExpenditureUi(
                binding,
                this
            )
        )
        mUi.makeValid(mId)
        mViewModel = ViewModelProvider(
            this,
            AExpenditureVMFactory(
                mId,
                requireContext(),
                this,
                this,
                arguments?.getLong("time")!!
            )
        ).get(AExpenditureVM::class.java)
        mViewModel.fetchExpenditure()
    }

    override fun onResume() {
        super.onResume()
        mViewModel.redefineReferences(this, this)
    }

    override fun onExpenditureObtained(expenditure: ExpenditureUi) {
        expenditure.map(mUi)
    }

    override fun onProductUpdated(stringId: Int) {
        Toast.makeText(
            requireContext(),
            stringId,
            Toast.LENGTH_SHORT
        ).show()
        Navigation.Navigation.instance().takeBack()
    }

    override fun onProductUpdated(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onClick(aeConfig: Int) {
        var nameAction = ""
        when (aeConfig) {
            AddEditConfig.valueAConfig() -> {
                mViewModel.insertExpenditure(mUi.expenditure())
                nameAction = "Add"
            }

            AddEditConfig.valueEConfig() -> {
                mViewModel.updateExpenditure(mUi.expenditure())
                nameAction = "Edit"
            }

            AddEditConfig.valueDConfig() -> {
                mViewModel.deleteExpenditure(
                    mUi.expenditure(
                        true
                    )
                )
                nameAction = "Delete"
            }
        }
        analytic()?.logEvent(
            "AEExpenditureFragment_action",
            bundleOf(Pair("Action", nameAction))
        )
    }
}