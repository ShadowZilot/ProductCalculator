package com.human_developing_soft.productcalc.profile.ui

import android.os.Bundle
import android.view.View
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import com.human_developing_soft.productcalc.BuildConfig
import com.human_developing_soft.productcalc.R
import com.human_developing_soft.productcalc.databinding.AboutAppFragmentBinding
import com.human_developing_soft.productcalc.feedback.ui.FeedbackFragment
import com.human_developing_soft.productcalc.main.ui.BaseFragment
import com.human_developing_soft.productcalc.navigation.Navigation
import com.human_developing_soft.productcalc.product_storage.StringContext
import com.human_developing_soft.productcalc.share_dialog.ShareAppFragment

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
class AboutAppFragment : BaseFragment<AboutAppFragmentBinding, Any>(
    bindingInflater = { inflater, container ->
        AboutAppFragmentBinding.inflate(inflater, container, false)
    }
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytic()?.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, "About App")
            param(FirebaseAnalytics.Param.SCREEN_CLASS, this::class.simpleName ?: "Unknown screen")
        }
        binding.versionTitle.text = StringContext.Base(requireContext()).string(
            R.string.version_label, BuildConfig.VERSION_NAME
        )
        binding.shareAppButton.setOnClickListener {
            Navigation.Navigation.instance().navigateTo(ShareAppFragment::class.java)
        }
        binding.feedbackButton.setOnClickListener {
            Navigation.Navigation.instance().navigateTo(FeedbackFragment::class.java)
        }
    }
}