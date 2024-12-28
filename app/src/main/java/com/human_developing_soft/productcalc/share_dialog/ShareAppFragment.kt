package com.human_developing_soft.productcalc.share_dialog

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat.getSystemService
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import com.human_developing_soft.productcalc.databinding.ShareAppFragmentBinding
import com.human_developing_soft.productcalc.main.domain.AnalyticsApp
import com.human_developing_soft.productcalc.main.ui.BaseFragment

class ShareAppFragment : BaseFragment<ShareAppFragmentBinding>(
    bindingInflater = { inflater, container ->
        ShareAppFragmentBinding.inflate(inflater, container, false)
    }
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as AnalyticsApp)
            .instance()
            ?.logEvent(FirebaseAnalytics.Event.SHARE) {}
        val clipboard =
            getSystemService(requireContext(), ClipboardManager::class.java)
        binding.copyLinkButton.setOnClickListener {
            clipboard?.setPrimaryClip(
                ClipData.newPlainText(
                    "AppShareLink",
                    "https://play.google.com/store/apps/details?id=com.human_developing_soft.productcalc",
                )
            )
        }
    }
}