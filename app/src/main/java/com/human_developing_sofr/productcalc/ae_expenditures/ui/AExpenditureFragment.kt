package com.human_developing_sofr.productcalc.ae_expenditures.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.human_developing_sofr.productcalc.databinding.AeExpenditureFieldsBinding

class AExpenditureFragment : Fragment() {
    private lateinit var mBinding: AeExpenditureFieldsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = AeExpenditureFieldsBinding.inflate(
            inflater,
            container,
            false
        )
        return mBinding.root
    }
}