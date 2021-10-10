package com.human_developing_sofr.productcalc.history.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.human_developing_sofr.productcalc.databinding.HistoryFragmentBinding

class HistoryFragment : Fragment() {
    private lateinit var mBinding: HistoryFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = HistoryFragmentBinding.inflate(inflater,
        container,
        false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}