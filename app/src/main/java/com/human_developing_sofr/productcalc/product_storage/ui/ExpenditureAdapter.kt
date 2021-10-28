package com.human_developing_sofr.productcalc.product_storage.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.human_developing_sofr.productcalc.databinding.ExpenditureItemBinding

class ExpenditureAdapter(
    private val mListener: OnExpenditureClickListener
): RecyclerView.Adapter<ExpenditureViewHolder>(), BaseAdapter<ExpenditureUi> {
    private val mExpenditures = mutableListOf<ExpenditureUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenditureViewHolder {
        return ExpenditureViewHolder(
            ExpenditureItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            mListener
        )
    }

    override fun onBindViewHolder(holder: ExpenditureViewHolder, position: Int) {
        mExpenditures[position].map(holder)
    }

    override fun getItemCount() = mExpenditures.size

    override fun fetchData(data: List<ExpenditureUi>) {
        mExpenditures.clear()
        mExpenditures.addAll(data)
        notifyDataSetChanged()
    }
}