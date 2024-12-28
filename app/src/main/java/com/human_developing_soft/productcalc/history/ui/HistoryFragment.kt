package com.human_developing_soft.productcalc.history.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import com.human_developing_soft.productcalc.databinding.HistoryFragmentBinding
import com.human_developing_soft.productcalc.history.domain.HistoryVM
import com.human_developing_soft.productcalc.history.domain.HistoryVMFactory
import com.human_developing_soft.productcalc.history.domain.OnHistoryObtained
import com.human_developing_soft.productcalc.main.data.SharedPreferencesShell
import com.human_developing_soft.productcalc.main.ui.BaseFragment
import com.human_developing_soft.productcalc.product_storage.ui.ProductsListEmptyView
import com.human_developing_soft.productcalc.sReviewGlobal

class HistoryFragment : BaseFragment<HistoryFragmentBinding>(
    bindingInflater = { inflater, container ->
        HistoryFragmentBinding.inflate(inflater, container, false)
    }
), OnDayItemClicked, OnHistoryObtained {
    private lateinit var mUiController: HistoryList
    private lateinit var mViewModel: HistoryVM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mUiController = HistoryList.Base(
            binding.historyFeedList,
            ProductsListEmptyView.Base(binding.emptyHistory),
            binding.historyTool,
            this
        )
        analytic()?.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, "History")
            param(FirebaseAnalytics.Param.SCREEN_CLASS, this::class.java.simpleName)
        }
        mViewModel = ViewModelProvider(
            this,
            HistoryVMFactory(
                requireContext(),
                this
            )
        )[HistoryVM::class.java]
        mViewModel.launchReview(
            SharedPreferencesShell.Base(
                requireContext(),
                sReviewGlobal
            )
        )
    }

    override fun onStart() {
        super.onStart()
        mViewModel.redefineReferences(this)
        mUiController.beginLoading()
        mViewModel.updateData(MonthProvider.Base(requireContext()))
    }

    override fun onDayClick(time: Long) {
        mViewModel.goToDay(time)
    }

    override fun onObtained(data: List<MonthUi>) {
        requireActivity().runOnUiThread {
            mUiController.fetchData(data)
        }
    }
}