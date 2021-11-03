package com.human_developing_soft.productcalc.add_editing.selecting_dialog.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.human_developing_soft.productcalc.add_editing.selecting_dialog.domain.SelectingVM
import com.human_developing_soft.productcalc.add_editing.selecting_dialog.domain.SelectingVMFactory
import com.human_developing_soft.productcalc.databinding.SelectingDialogBinding

class SelectingDialog : DialogFragment(), OnSelectingTypeGot {
    private lateinit var mViewModel: SelectingVM
    private lateinit var mBinding: SelectingDialogBinding
    private var mType: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mViewModel = ViewModelProvider(
                this, SelectingVMFactory(
                    requireContext(),
                    this,
                    it.getLong("time"),
                    it.getInt("id")
                )
            ).get(SelectingVM::class.java)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mBinding = SelectingDialogBinding.inflate(LayoutInflater.from(context))
        return if (mType == 0) {
            AlertDialog.Builder(requireContext())
                .setView(mBinding.root)
                .create()
        } else {
            dismiss()
            mViewModel.navigateToSomeFragment()
            super.onCreateDialog(savedInstanceState)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return mBinding.root
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
    }

    override fun onGot(type: Int) {
        try {
            if (type == 0) {
                mBinding.root.visibility = View.VISIBLE
            } else {
                mViewModel.navigateToSomeFragment(type)
            }
            dismiss()
        } catch (e: UninitializedPropertyAccessException) {
            mType = type
        }
    }
}