package com.human_developing_sofr.productcalc.ae_products.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuPresenter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.human_developing_sofr.productcalc.R
import com.human_developing_sofr.productcalc.ae_products.domain.AEProductVM
import com.human_developing_sofr.productcalc.ae_products.domain.AEProductVMFactory
import com.human_developing_sofr.productcalc.ae_products.domain.OnProductObtained
import com.human_developing_sofr.productcalc.ae_products.domain.OnProductUpdatedListener
import com.human_developing_sofr.productcalc.databinding.AeProductsFragmentBinding
import com.human_developing_sofr.productcalc.product_storage.Navigation
import com.human_developing_sofr.productcalc.product_storage.ui.ProductUi

class AEProductFragment : Fragment(), OnProductUpdatedListener, OnProductObtained {
    private lateinit var mBinding: AeProductsFragmentBinding
    private lateinit var mViewModel: AEProductVM
    private lateinit var mFields: ProductFieldsImpl
    private var mId : Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mId = arguments?.getInt("id")
        if (mId == -1) mId = null
        mBinding = AeProductsFragmentBinding.inflate(
            inflater, container, false
        )
        mFields = ProductFieldsImpl(mBinding.aeFields, mId)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mViewModel = ViewModelProvider(this,
            AEProductVMFactory(requireContext(),
            this, this,
            arguments?.getLong("time"))
        ).get(AEProductVM::class.java)
        if (mId == null) {
            mBinding.aeToolBar.menu.getItem(0).isVisible = false
            mBinding.aeAddButton.setOnClickListener {
                mViewModel.saveProduct(
                    mFields.product()
                )
            }
        } else {
            mBinding.aeToolBar.menu.getItem(0).setOnMenuItemClickListener {
                mViewModel.deleteProduct(
                    mFields.product()
                )
                true
            }
            mBinding.aeToolBar.setTitle(R.string.editing_tool_label)
            mBinding.aeAddButton.setText(R.string.edit_button_label)
            mViewModel.productById(mId!!)
            mBinding.aeAddButton.setOnClickListener {
                mViewModel.updateProduct(
                    mFields.product()
                )
            }
        }
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
                mFields
            )
        }
    }
}