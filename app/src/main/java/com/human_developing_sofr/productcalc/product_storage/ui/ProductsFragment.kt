package com.human_developing_sofr.productcalc.product_storage.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.human_developing_sofr.productcalc.ae_products.ui.AEProductFragment
import com.human_developing_sofr.productcalc.databinding.ProductsFragmentBinding
import com.human_developing_sofr.productcalc.history.ui.HistoryFragment
import com.human_developing_sofr.productcalc.product_storage.Navigation
import com.human_developing_sofr.productcalc.product_storage.domain.ProductListVMFactory
import com.human_developing_sofr.productcalc.product_storage.domain.ProductsListVM
import com.human_developing_sofr.productcalc.product_storage.domain.ProductsObserver
import java.util.*

class ProductsFragment : Fragment(), ProductsObserver, OnProductClickListener {
    private lateinit var mBinding: ProductsFragmentBinding
    private lateinit var mListManager: AllProductsView
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
        mListManager = AllProductsView.Base(
            mBinding.productsList,
            ProductsListEmptyView.Base(
                mBinding.emptyListProducts
            ),
            this
        )
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mViewModel = ViewModelProvider(this, ProductListVMFactory(
            this,
            Date().time,
            requireContext()
        )).get(ProductsListVM::class.java)
        mBinding.addProductButton.setOnClickListener {
            Navigation.Navigation.instance().navigateTo(
                AEProductFragment::class.java
            )
        }
        mBinding.productsToolbar.menu.getItem(0).setOnMenuItemClickListener {
            Navigation.Navigation.instance().navigateTo(
                HistoryFragment::class.java
            )
            true
        }
    }

    override fun onStart() {
        super.onStart()
        mListManager.beginLoading()
        mViewModel.fetchProducts()
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel.onCancel()
    }

    override fun onUpdatedProducts(day: AllDayUi) {
        // TODO Implement this with new ui object
    }

    override fun onError(stringRes: Int) {
        requireActivity().runOnUiThread {
            mListManager.fetchData(emptyList(), stringRes)
        }
    }

    override fun onProductClick(id: Int) {
        mViewModel.navigateToAdding(id)
    }
}