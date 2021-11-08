package com.human_developing_soft.productcalc.product_storage.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.human_developing_soft.productcalc.databinding.CollapsingItemBinding
import com.human_developing_soft.productcalc.databinding.ExpenditureItemBinding
import com.human_developing_soft.productcalc.databinding.ProductItemBinding

class MainProductAdapter(
    private val mClickListener: OnProductClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    CollapsingListener, OnListDiffCalculated {
    private val mList = WholeList.Base(this)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            0 -> ProductViewHolder(ProductItemBinding.inflate(inflater, parent, false),
                mClickListener
            )
            1 -> ExpenditureViewHolder(
                ExpenditureItemBinding.inflate(inflater, parent, false),
                mClickListener
            )
            2 -> CollapsingViewHolder(
                CollapsingItemBinding.inflate(inflater, parent, false),
                this
            )
            else -> throw IllegalStateException("Illegal viewType = $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ProductViewHolder -> (mList[position] as ProductUi.Base).map(holder)
            is ExpenditureViewHolder -> (mList[position] as ExpenditureUi.Base).map(holder)
            is CollapsingViewHolder -> (mList[position] as CollapsingItemUi.Base).map(holder)
        }
    }

    override fun getItemViewType(position: Int) = when (mList[position]) {
        is ProductUi.Base -> 0
        is ExpenditureUi.Base -> 1
        is CollapsingItemUi.Base -> 2
        else -> -1
    }

    override fun getItemCount() = mList.size()

    fun fetchData(
        products: List<ProductUi>,
        expenditure: List<ExpenditureUi>
    ) {
        mList.fetchList(products, expenditure)
    }

    override fun onItemCollapsed(type: Int) {
        mList.onItemCollapsed(type)
    }

    override fun onCalculated(result: DiffUtil.DiffResult) {
        result.dispatchUpdatesTo(this)
    }
}