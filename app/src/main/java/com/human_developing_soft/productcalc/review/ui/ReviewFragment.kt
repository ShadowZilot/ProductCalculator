package com.human_developing_soft.productcalc.review.ui

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.human_developing_soft.productcalc.AnalyticsApp
import com.human_developing_soft.productcalc.SharedPreferencesShell
import com.human_developing_soft.productcalc.databinding.ReviewFragmentBinding
import com.human_developing_soft.productcalc.review.domain.sLastReview
import com.human_developing_soft.productcalc.sReviewGlobal
import java.util.*

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
class ReviewFragment : BottomSheetDialogFragment() {
    private lateinit var mBinding : ReviewFragmentBinding
    private var mIsGoRate = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = ReviewFragmentBinding.inflate(layoutInflater,
            container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as AnalyticsApp)
            .instance()
            ?.logEvent("Rate_opened", Bundle())
        if (savedInstanceState != null) {
            mIsGoRate = savedInstanceState.getBoolean("isGoRate")
        }
        mBinding.rateButton.setOnClickListener {
            val rateIntent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(
                    "https://play.google.com/store/apps/details?id=com.human_developing_soft.productcalc"
                )
            }
            startActivity(rateIntent)
            (requireActivity().application as AnalyticsApp)
                .instance()
                ?.logEvent("User_go_to_rate", Bundle())
            SharedPreferencesShell.Base(requireContext(),
                sReviewGlobal).edit {
                    it.putLong(sLastReview, Date().time)
                    it.apply()
            }
            mIsGoRate = true
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("isGoRate", mIsGoRate)
    }

    override fun onResume() {
        super.onResume()
        if (mIsGoRate) dismiss()
    }

    override fun onDismiss(dialog: DialogInterface) {
        if (!mIsGoRate) {
            (requireActivity().application as AnalyticsApp)
                .instance()
                ?.logEvent("User_dismiss_rate", Bundle())
        }
    }
}