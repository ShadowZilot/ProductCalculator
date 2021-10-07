package com.human_developing_sofr.productcalc.product_storage.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.human_developing_sofr.productcalc.ae_products.ui.AEProductFragment
import com.human_developing_sofr.productcalc.databinding.ProductsFragmentBinding
import com.human_developing_sofr.productcalc.product_storage.Navigation
import com.human_developing_sofr.productcalc.product_storage.domain.ProductListVMFactory
import com.human_developing_sofr.productcalc.product_storage.domain.ProductsListVM
import com.human_developing_sofr.productcalc.product_storage.domain.ProductsObserver

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
                mBinding.productsProgressLoad,
                mBinding.emptyProgressText
            ),
            this
        )
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mViewModel = ViewModelProvider(this, ProductListVMFactory(
            this,
            requireContext()
        )).get(ProductsListVM::class.java)
        mBinding.addProductButton.setOnClickListener {
            Navigation.Navigation.instance().navigateTo(
                AEProductFragment::class.java
            )
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

    override fun updatedProducts(products: List<ProductUi>) {
        requireActivity().runOnUiThread{
            mListManager.fetchData(products)
        }
    }

    override fun onProductClick(id: Int) {

    }
}