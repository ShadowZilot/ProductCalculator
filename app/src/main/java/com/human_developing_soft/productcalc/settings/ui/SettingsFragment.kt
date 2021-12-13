package com.human_developing_soft.productcalc.settings.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.human_developing_soft.productcalc.databinding.SettingsFragmentBinding

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
class SettingsFragment : Fragment() {
    private lateinit var mBinding: SettingsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = SettingsFragmentBinding.inflate(inflater,
            container, false)
        return mBinding.root
    }
}