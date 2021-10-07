package com.human_developing_sofr.productcalc.ae_products.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.human_developing_sofr.productcalc.databinding.AeProductsFragmentBinding

class AEProductFragment : Fragment() {
    private lateinit var mBinding: AeProductsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = AeProductsFragmentBinding.inflate(
            inflater, container, false
        )
        return mBinding.root
    }
}