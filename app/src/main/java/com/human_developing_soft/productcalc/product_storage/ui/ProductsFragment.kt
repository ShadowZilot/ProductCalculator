package com.human_developing_soft.productcalc.product_storage.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import com.human_developing_soft.productcalc.main.ui.BaseFragment
import com.human_developing_soft.productcalc.databinding.ProductsFragmentBinding
import com.human_developing_soft.productcalc.history.ui.DateProvider
import com.human_developing_soft.productcalc.product_storage.domain.ProductListVMFactory
import com.human_developing_soft.productcalc.product_storage.domain.ProductsListVM
import com.human_developing_soft.productcalc.product_storage.domain.ProductsObserver

class ProductsFragment : BaseFragment(), ProductsObserver,
    OnProductClickListener, OnDayEditing, FragmentResultListener {
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
                    mBinding.addProductButton
                ),
                ProductsListEmptyView.Base(
                    mBinding.emptyListProducts
                )
            )
        )
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        analytic()?.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, "ProductsList")
            param(FirebaseAnalytics.Param.SCREEN_CLASS, this::class.java.simpleName)
        }
        mViewModel = ViewModelProvider(
            this, ProductListVMFactory(
                this,
                arguments?.getLong("time"),
                requireContext()
            )
        )[ProductsListVM::class.java]
        mBinding.addProductButton.setOnClickListener {
            mViewModel.navigateToAdding()
        }
        arguments?.getLong("time")?.let {
            mBinding.productsToolbar.title = DateProvider.Base(
                it, requireContext()
            ).date()
        }
        mViewModel.visibilityForAdding().let {
            mBinding.moneyInfo.allMoneyEdit.visibility = it
            if (it == View.GONE) {
                mBinding.addProductButton.hide()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mViewModel.redefineReferences(this)
        mUiManager.startLoading()
        mViewModel.fetchProducts()
        mViewModel.visibilityForAdding().let {
            if (it == View.GONE) {
                mBinding.addProductButton.hide()
            }
        }
    }

    override fun onUpdatedProducts(day: AllDayUi) {
        day.map(mUiManager)
    }

    override fun onError(stringRes: Int, isToast: Boolean) {
        if (isToast) {
            Toast.makeText(
                requireContext(),
                stringRes,
                Toast.LENGTH_SHORT
            ).show()
        } else {
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
}