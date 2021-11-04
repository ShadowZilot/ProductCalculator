package com.human_developing_soft.productcalc.edit_day_money.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.human_developing_soft.productcalc.databinding.DayMoneyEditingBinding
import com.human_developing_soft.productcalc.edit_day_money.domain.EditMoneyVM
import com.human_developing_soft.productcalc.edit_day_money.domain.EditMoneyVMFactory

class EditingMoneyFragment : DialogFragment(), DayObserver, DayUpdating {
    private lateinit var mBinding : DayMoneyEditingBinding
    private lateinit var mViewModel : EditMoneyVM

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mBinding = DayMoneyEditingBinding.inflate(layoutInflater)
        return AlertDialog.Builder(context)
            .setView(mBinding.root)
            .create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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