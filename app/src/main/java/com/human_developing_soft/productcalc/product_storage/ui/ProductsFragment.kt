package com.human_developing_soft.productcalc.product_storage.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import com.human_developing_soft.productcalc.databinding.ProductsFragmentBinding
import com.human_developing_soft.productcalc.history.ui.DateProvider
import com.human_developing_soft.productcalc.main.ui.BaseFragment
import com.human_developing_soft.productcalc.product_storage.domain.ProductListVMFactory
import com.human_developing_soft.productcalc.product_storage.domain.ProductsListVM
import com.human_developing_soft.productcalc.product_storage.domain.ProductsObserver

class ProductsFragment : BaseFragment<ProductsFragmentBinding, Any>(
    bindingInflater = { inflater, container ->
        ProductsFragmentBinding.inflate(inflater, container, false)
    }
), ProductsObserver,
    OnProductClickListener, OnDayEditing, FragmentResultListener {
    private lateinit var mUiManager: DayPresentation
    private lateinit var mViewModel: ProductsListVM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mUiManager = DayPresentation.Base(
            MoneyInfoView.Base(
                binding.moneyInfo,
                this
            ),
            AllProductsView.Base(
                CollapsingList.Base(
                    binding.productsList,
                    this,
                    binding.addProductButton
                ),
                ProductsListEmptyView.Base(
                    binding.emptyListProducts
                )
            )
        )
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
        binding.addProductButton.setOnClickListener {
            mViewModel.navigateToAdding()
        }
        arguments?.getLong("time")?.let {
            binding.productsToolbar.title = DateProvider.Base(
                it, requireContext()
            ).date()
        }
        mViewModel.visibilityForAdding().let {
            binding.moneyInfo.allMoneyEdit.visibility = it
            if (it == View.GONE) {
                binding.addProductButton.hide()
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
                binding.addProductButton.hide()
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