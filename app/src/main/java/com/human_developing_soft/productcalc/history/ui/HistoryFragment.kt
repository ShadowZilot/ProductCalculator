package com.human_developing_soft.productcalc.history.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.human_developing_soft.productcalc.databinding.HistoryFragmentBinding
import com.human_developing_soft.productcalc.history.domain.HistoryVM
import com.human_developing_soft.productcalc.history.domain.HistoryVMFactory
import com.human_developing_soft.productcalc.history.domain.OnHistoryObtained
import com.human_developing_soft.productcalc.product_storage.ui.ProductsListEmptyView

class HistoryFragment : Fragment(), OnDayItemClicked, OnHistoryObtained {
    private lateinit var mBinding: HistoryFragmentBinding
    private lateinit var mUiController: HistoryList
    private lateinit var mViewModel: HistoryVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = HistoryFragmentBinding.inflate(inflater,
        container,
        false)
        mUiController = HistoryList.Base(
            mBinding.historyFeedList,
            ProductsListEmptyView.Base(
                mBinding.emptyHistory
            ),
            mBinding.historyTool,
            this
        )
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mViewModel = ViewModelProvider(this,
            HistoryVMFactory(requireContext(),
                this)
        ).get(HistoryVM::class.java)
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