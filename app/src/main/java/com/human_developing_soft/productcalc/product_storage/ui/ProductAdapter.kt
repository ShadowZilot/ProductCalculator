package com.human_developing_soft.productcalc.product_storage.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.human_developing_soft.productcalc.databinding.ProductItemBinding

class ProductAdapter(
    private val mListener: OnProductClickListener
) : RecyclerView.Adapter<ProductViewHolder>(), BaseAdapter<ProductUi> {
    private val mProducts = mutableListOf<ProductUi>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        return ProductViewHolder(
            ProductItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            ),
            mListener
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        mProducts[position].map(
            holder
        )
    }

    override fun getItemCount() = mProducts.size

    override fun fetchData(data: List<ProductUi>) {
        mProducts.clear()
        mProducts.addAll(data)
        notifyDataSetChanged()
    }
}