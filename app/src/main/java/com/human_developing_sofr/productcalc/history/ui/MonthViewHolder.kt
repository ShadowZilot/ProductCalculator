package com.human_developing_sofr.productcalc.history.ui

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.human_developing_sofr.productcalc.databinding.HistoryDayItemBinding
import com.human_developing_sofr.productcalc.databinding.HistoryItemBinding
import java.util.*

class MonthViewHolder(
    private val mBinding: HistoryItemBinding,
    private val mObserver: OnDayItemClicked
) : RecyclerView.ViewHolder(mBinding.root), MonthUi.Mapper<Unit> {
    override fun map(name: String, days: List<Long>) {
        mBinding.monthNameView.text = name
        for (day in days) {
            val dayItem = HistoryDayItemBinding.inflate(
                LayoutInflater.from(mBinding.root.context))
            dayItem.dayNumber.text = day.byDay()
            dayItem.root.setOnClickListener {
                mObserver.onDayClick(day)
            }
            mBinding.daysGrid.addView(dayItem.root)
        }
    }
}

fun Long.byDay(): String {
    val calendar = GregorianCalendar.getInstance()
    calendar.time = Date(this)
    return calendar.get(Calendar.DAY_OF_MONTH).toString()
}