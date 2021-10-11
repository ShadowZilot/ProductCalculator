package com.human_developing_sofr.productcalc.history.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.human_developing_sofr.productcalc.databinding.HistoryItemBinding

class HistoryAdapter(
    private val mObserver: OnDayItemClicked
) : RecyclerView.Adapter<MonthViewHolder>() {
    private val mData = mutableListOf<MonthUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthViewHolder {
        return MonthViewHolder(
            HistoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            mObserver
        )
    }

    override fun onBindViewHolder(holder: MonthViewHolder, position: Int) {
        mData[position].map(
            holder
        )
    }

    override fun getItemCount() = mData.size

    fun fetchData(data: List<MonthUi>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }
}