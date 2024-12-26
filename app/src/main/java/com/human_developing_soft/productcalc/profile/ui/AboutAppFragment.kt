package com.human_developing_soft.productcalc.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import com.human_developing_soft.productcalc.main.ui.BaseFragment
import com.human_developing_soft.productcalc.BuildConfig
import com.human_developing_soft.productcalc.R
import com.human_developing_soft.productcalc.databinding.AboutAppFragmentBinding
import com.human_developing_soft.productcalc.navigation.Navigation
import com.human_developing_soft.productcalc.product_storage.StringContext
import com.human_developing_soft.productcalc.share_dialog.ShareAppFragment

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
class AboutAppFragment : BaseFragment() {
    private lateinit var mBinding: AboutAppFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = AboutAppFragmentBinding.inflate(
            inflater,
            container, false
        )
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytic()?.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, "About App")
            param(FirebaseAnalytics.Param.SCREEN_CLASS, this::class.simpleName ?: "Unknown screen")
        }
        mBinding.versionTitle.text = StringContext.Base(requireContext()).string(
            R.string.version_label, BuildConfig.VERSION_NAME
        )
        mBinding.shareAppButton.setOnClickListener {
            Navigation.Navigation.instance().navigateTo(ShareAppFragment::class.java)
        }
    }
}