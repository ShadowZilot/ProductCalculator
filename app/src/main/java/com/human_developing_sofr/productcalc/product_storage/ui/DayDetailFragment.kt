package com.human_developing_sofr.productcalc.product_storage.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.human_developing_sofr.productcalc.R
import com.human_developing_sofr.productcalc.ae_products.ui.AEProductFragment
import com.human_developing_sofr.productcalc.databinding.DayDetailFragmentBinding
import com.human_developing_sofr.productcalc.history.ui.DateProvider
import com.human_developing_sofr.productcalc.product_storage.Navigation
import com.human_developing_sofr.productcalc.product_storage.StringContext
import com.human_developing_sofr.productcalc.product_storage.domain.ProductListVMFactory
import com.human_developing_sofr.productcalc.product_storage.domain.ProductsListVM
import com.human_developing_sofr.productcalc.product_storage.domain.ProductsObserver

class DayDetailFragment : Fragment(), OnProductClickListener, ProductsObserver {
    private lateinit var mBinding: DayDetailFragmentBinding
    private lateinit var mListManager: AllProductsView
    private lateinit var mViewModel: ProductsListVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DayDetailFragmentBinding.inflate(
            inflater, container, false
        )
        mListManager = AllProductsView.Base(
            mBinding.dayList,
            ProductsListEmptyView.Base(
                mBinding.emptyListDetail
            ),
            this
        )
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val time = requireArguments().getLong("time")
        mViewModel = ViewModelProvider(this,
                    ProductListVMFactory(this,
                        time,
                        requireContext())
        ).get(ProductsListVM::class.java)
        mBinding.addDetailButton.setOnClickListener {
            mViewModel.navigateToAdding()
        }
        mBinding.addDetailButton.visibility = mViewModel.visibilityForAdding()
    }

    override fun onStart() {
        super.onStart()
        mListManager.beginLoading()
        mViewModel.fetchProducts()
    }

    override fun onUpdatedProducts(day: AllDayUi) {
        // TODO Implement this method with new UI object
    }

    override fun onError(stringRes: Int) {
        // TODO Implement on error method
    }

    override fun onProductClick(id: Int) {
        mViewModel.navigateToAdding(id)
    }
}