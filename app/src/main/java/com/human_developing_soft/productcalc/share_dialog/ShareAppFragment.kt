package com.human_developing_soft.productcalc.share_dialog

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import com.human_developing_soft.productcalc.databinding.ShareAppFragmentBinding
import com.human_developing_soft.productcalc.main.domain.AnalyticsApp

class ShareAppFragment : Fragment() {
    private var _binding: ShareAppFragmentBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ShareAppFragmentBinding.inflate(
            inflater,
            container,
            false,
        )
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as AnalyticsApp)
            .instance()
            ?.logEvent(FirebaseAnalytics.Event.SHARE) {}
        val clipboard =
            getSystemService(requireContext(), ClipboardManager::class.java)
        mBinding.copyLinkButton.setOnClickListener {
            clipboard?.setPrimaryClip(
                ClipData.newPlainText(
                    "AppShareLink",
                    "https://play.google.com/store/apps/details?id=com.human_developing_soft.productcalc",
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}