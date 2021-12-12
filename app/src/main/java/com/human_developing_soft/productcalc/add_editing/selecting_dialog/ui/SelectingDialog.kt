package com.human_developing_soft.productcalc.add_editing.selecting_dialog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.human_developing_soft.productcalc.R
import com.human_developing_soft.productcalc.add_editing.selecting_dialog.domain.SelectingVM
import com.human_developing_soft.productcalc.add_editing.selecting_dialog.domain.SelectingVMFactory
import com.human_developing_soft.productcalc.databinding.SelectingDialogBinding

class SelectingDialog : BottomSheetDialogFragment(), OnSelectingTypeGot {
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
            )[SelectingVM::class.java]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = SelectingDialogBinding.inflate(layoutInflater)
        dialog?.setTitle(R.string.selecting_title)
        return if (mType == 0) {
            mBinding.root
        } else {
            dismiss()
            mViewModel.navigateToSomeFragment()
            null
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