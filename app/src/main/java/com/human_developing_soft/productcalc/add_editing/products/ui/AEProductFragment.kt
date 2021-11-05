package com.human_developing_soft.productcalc.add_editing.products.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.human_developing_soft.productcalc.add_editing.common_ui.AddEditRestUi
import com.human_developing_soft.productcalc.add_editing.common_ui.OnChangingButtonClick
import com.human_developing_soft.productcalc.add_editing.common_ui.OnChangingButtonClick.AddEditConfig
import com.human_developing_soft.productcalc.add_editing.products.domain.AEProductVM
import com.human_developing_soft.productcalc.add_editing.products.domain.AEProductVMFactory
import com.human_developing_soft.productcalc.add_editing.products.domain.OnProductObtained
import com.human_developing_soft.productcalc.add_editing.products.domain.OnProductUpdatedListener
import com.human_developing_soft.productcalc.databinding.AeProductsFragmentBinding
import com.human_developing_soft.productcalc.navigation.Navigation
import com.human_developing_soft.productcalc.product_storage.ui.ProductUi

class AEProductFragment : Fragment(),
    OnProductUpdatedListener, OnProductObtained, OnChangingButtonClick {
    private lateinit var mBinding: AeProductsFragmentBinding
    private lateinit var mViewModel: AEProductVM
    private lateinit var mUiManager: AEProductUi
    private var mId : Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = AeProductsFragmentBinding.inflate(
            inflater, container, false
        )
        mId = arguments?.getInt("id")
        if (mId == -1) mId = null
        mUiManager = AEProductUi.Base(
            ProductFieldsImpl(
                mBinding.aeFields,
                mId
            ),
            AddEditRestUi.AEProductUi(
                mBinding, this
            )
        )
        mUiManager.makeValid(mId)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mViewModel = ViewModelProvider(this,
            AEProductVMFactory(requireContext(),
            this, this,
            arguments?.getLong("time"))
        ).get(AEProductVM::class.java)
        mViewModel.productById(mId)
    }

    override fun onProductUpdated(stringId: Int) {
        requireActivity().runOnUiThread {
            Toast.makeText(
                requireContext(),
                stringId,
                Toast.LENGTH_SHORT
            ).show()
            Navigation.Navigation.instance().takeBack()
        }
    }

    override fun onProductUpdated(message: String) {
        requireActivity().runOnUiThread {
            Toast.makeText(
                requireContext(),
                message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onProductObtained(product: ProductUi) {
        requireActivity().runOnUiThread {
            product.map(
                mUiManager
            )
        }
    }

    override fun onClick(aeConfig: Int) {
        when(aeConfig) {
            AddEditConfig.valueAConfig() -> mViewModel.saveProduct(mUiManager.product())
            AddEditConfig.valueEConfig() -> mViewModel.updateProduct(mUiManager.product())
            AddEditConfig.valueDConfig() -> mViewModel.deleteProduct(mUiManager.product())
        }
    }
}