package com.human_developing_sofr.productcalc.product_storage.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.human_developing_sofr.productcalc.databinding.ProductItemBinding

class CollapsingAdapter(
    private val mListener: OnProductClickListener
) : RecyclerView.Adapter<ProductViewHolder>(), BaseAdapter {
    private val mProducts = mutableListOf<ProductUi>()
    private val mExpenditures = mutableListOf<ExpenditureUi>()

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ProductItemBinding.inflate(LayoutInflater.from(
                parent.context
            ), parent, false),
        mListener)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        mProducts[position].map(
            holder
        )
    }

    override fun getItemCount() = mProducts.size

    override fun fetchData(products: List<ProductUi>,
                           expenditure: List<ExpenditureUi>) {
        mProducts.clear()
        mProducts.addAll(products)
        mExpenditures.clear()
        mExpenditures.addAll(expenditure)
        notifyDataSetChanged()
    }
}