package com.human_developing_soft.productcalc.edit_day_money.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.human_developing_soft.productcalc.R
import com.human_developing_soft.productcalc.databinding.DayMoneyEditingBinding
import com.human_developing_soft.productcalc.edit_day_money.domain.EditMoneyVM
import com.human_developing_soft.productcalc.edit_day_money.domain.EditMoneyVMFactory

class EditingMoneyFragment : BottomSheetDialogFragment(), DayObserver, DayUpdating {
    private lateinit var mBinding: DayMoneyEditingBinding
    private lateinit var mViewModel: EditMoneyVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DayMoneyEditingBinding.inflate(layoutInflater)
        mBinding.moneyInput.setupKeyboardComponents(
            mBinding.keyboardContainer,
            requireActivity(),
            null
        )
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mViewModel = ViewModelProvider(
            this, EditMoneyVMFactory(
                requireArguments().getInt("dayId"),
                arguments?.getLong("time"),
                requireContext(),
                this,
                this
            )
        )[EditMoneyVM::class.java]
        mBinding.applyButton.setOnClickListener {
            if (mBinding.moneyInput.isTextValid()) {
                mViewModel.updateDay(
                    mBinding.moneyInput.numberedText()
                )
            } else {
                Toast.makeText(
                    context,
                    R.string.not_numbered_text,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        mBinding.cancelButton.setOnClickListener {
            dismiss()
        }
        if (savedInstanceState != null) {
            mBinding.moneyInput.setText(
                savedInstanceState.getInt("savedMoney").toString()
            )
        } else {
            mViewModel.fetchDay()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(
            "savedMoney",
            mBinding.moneyInput.text.toString().toInt()
        )
    }

    override fun onResume() {
        super.onResume()
        mViewModel.redefineReferences(this, this)
    }

    override fun onDayObtain(money: Int) {
        mBinding.moneyInput.setText(money.toString())
    }

    override fun onDayUpdated() {
        requireActivity().runOnUiThread {
            parentFragmentManager.setFragmentResult("result", Bundle())
            dismiss()
        }
    }
}