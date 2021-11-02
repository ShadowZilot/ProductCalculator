package com.human_developing_sofr.productcalc.add_editing.selecting_dialog.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.human_developing_sofr.productcalc.add_editing.selecting_dialog.domain.SelectingVM
import com.human_developing_sofr.productcalc.add_editing.selecting_dialog.domain.SelectingVMFactory
import com.human_developing_sofr.productcalc.databinding.SelectingDialogBinding

class SelectingDialog : DialogFragment() {
    private lateinit var mViewModel: SelectingVM
    private var mIsUiHas: Int  = 0
    private lateinit var mBinding: SelectingDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mViewModel = ViewModelProvider(
                this, SelectingVMFactory(
                    requireContext(),
                    it.getLong("time"),
                    it.getInt("id")
                )
            ).get(SelectingVM::class.java)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mIsUiHas = mViewModel.type()
        mBinding = SelectingDialogBinding.inflate(LayoutInflater.from(context))
        return AlertDialog.Builder(requireContext())
            .setView(mBinding.root)
            .create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (mIsUiHas == 0) {
             null
        } else {
            mBinding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mBinding.expenditureSelectingContainer.setOnClickListener {
            mViewModel.navigateToSomeFragment(2)
            dismiss()
        }
        mBinding.productSelectingContainer.setOnClickListener {
            mViewModel.navigateToSomeFragment(1)
            dismiss()
        }
        mBinding.selectingCancelButton.setOnClickListener {
            dismiss()
        }
        mViewModel.navigateToSomeFragment(mIsUiHas)
    }
}