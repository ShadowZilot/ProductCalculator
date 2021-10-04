package com.human_developing_sofr.productcalc.product_storage.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.human_developing_sofr.productcalc.databinding.ProductsFragmentBinding
import com.human_developing_sofr.productcalc.product_storage.domain.ProductListVMFactory
import com.human_developing_sofr.productcalc.product_storage.domain.ProductsListVM
import com.human_developing_sofr.productcalc.product_storage.domain.ProductsObserver

class ProductsFragment : Fragment(), ProductsObserver {
    private lateinit var mBinding: ProductsFragmentBinding
    private lateinit var mProgressManager: ProductsListEmptyView
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
        mProgressManager = ProductsListEmptyView.Base(
            mBinding.productListEmpty
        )
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mViewModel = ViewModelProvider(this, ProductListVMFactory(
            this,
            requireContext()
        )).get(ProductsListVM::class.java)
    }

    override fun onStart() {
        super.onStart()
        mProgressManager.beginLoading()
        mViewModel.fetchProducts()
    }

    override fun updatedProducts(products: List<ProductUi>) {
        mProgressManager.stopLoading(
            products.isNotEmpty()
        )
    }
}