package com.human_developing_soft.productcalc.product_storage.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.human_developing_soft.productcalc.databinding.ProductsFragmentBinding
import com.human_developing_soft.productcalc.history.ui.DateProvider
import com.human_developing_soft.productcalc.history.ui.HistoryFragment
import com.human_developing_soft.productcalc.product_storage.Navigation
import com.human_developing_soft.productcalc.product_storage.domain.ProductListVMFactory
import com.human_developing_soft.productcalc.product_storage.domain.ProductsListVM
import com.human_developing_soft.productcalc.product_storage.domain.ProductsObserver

class ProductsFragment : Fragment(), ProductsObserver,
    OnProductClickListener, OnDayEditing, OnExpenditureClickListener, FragmentResultListener {
    private lateinit var mBinding: ProductsFragmentBinding
    private lateinit var mUiManager: DayPresentation
    private lateinit var mViewModel: ProductsListVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = ProductsFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        mUiManager = DayPresentation.Base(
            MoneyInfoView.Base(
                mBinding.moneyInfo,
                this
            ),
            AllProductsView.Base(
                CollapsingList.Base(
                    mBinding.productsList,
                    this,
                    this
                ),
                ProductsListEmptyView.Base(
                    mBinding.emptyListProducts
                )
            )
        )
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mViewModel = ViewModelProvider(
            this, ProductListVMFactory(
                this,
                arguments?.getLong("time"),
                requireContext()
            )
        ).get(ProductsListVM::class.java)
        mBinding.addProductButton.setOnClickListener {
            mViewModel.navigateToAdding()
        }
        mBinding.productsToolbar.menu.getItem(0).setOnMenuItemClickListener {
            Navigation.Navigation.instance().navigateTo(
                HistoryFragment::class.java
            )
            true
        }
        arguments?.getLong("time")?.let {
            mBinding.productsToolbar.menu.getItem(0).isVisible = false
            mBinding.productsToolbar.title = DateProvider.Base(
                it, requireContext()
            ).date()
        }
        mViewModel.visibilityForAdding().let {
            mBinding.moneyInfo.allMoneyEdit.visibility = it
            mBinding.addProductButton.visibility = it
        }
    }

    override fun onStart() {
        super.onStart()
        mUiManager.startLoading()
        mViewModel.fetchProducts()
    }

    override fun onUpdatedProducts(day: AllDayUi) {
        requireActivity().runOnUiThread {
            day.map(mUiManager)
        }
    }

    override fun onError(stringRes: Int) {
        requireActivity().runOnUiThread {
            mUiManager.raiseError(stringRes)
        }
    }

    override fun onProductClick(id: Int) {
        mViewModel.navigateToAdding(id)
    }

    override fun onDayEdit() {
        mViewModel.navigateToEditingDay(this)
    }

    override fun onFragmentResult(requestKey: String, result: Bundle) {
        mUiManager.startLoading()
        mViewModel.fetchProducts()
    }

    // TODO Collapse methods with same signatures to single method
    override fun onClick(id: Int) {
        mViewModel.navigateToAdding(id)
    }
}