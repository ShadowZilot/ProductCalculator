package com.human_developing_soft.productcalc.edit_day_money.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.human_developing_soft.productcalc.databinding.DayMoneyEditingBinding
import com.human_developing_soft.productcalc.edit_day_money.domain.EditMoneyVM
import com.human_developing_soft.productcalc.edit_day_money.domain.EditMoneyVMFactory

class EditingMoneyFragment : BottomSheetDialogFragment(), DayObserver, DayUpdating {
    private lateinit var mBinding : DayMoneyEditingBinding
    private lateinit var mViewModel : EditMoneyVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DayMoneyEditingBinding.inflate(layoutInflater)
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
        ).get(EditMoneyVM::class.java)
        mBinding.applyButton.setOnClickListener {
            mViewModel.updateDay(
                mBinding.moneyInput.text?.toString()?.toInt()!!
            )
        }
        mBinding.cancelButton.setOnClickListener {
            dismiss()
        }
        mViewModel.fetchDay()
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