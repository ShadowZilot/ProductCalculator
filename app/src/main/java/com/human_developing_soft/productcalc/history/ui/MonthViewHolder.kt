package com.human_developing_soft.productcalc.history.ui

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.human_developing_soft.productcalc.databinding.HistoryDayItemBinding
import com.human_developing_soft.productcalc.databinding.HistoryItemBinding
import java.util.*

class MonthViewHolder(
    private val mBinding: HistoryItemBinding,
    private val mObserver: OnDayItemClicked
) : RecyclerView.ViewHolder(mBinding.root), MonthUi.Mapper<Unit> {
    override fun map(name: String, days: List<Long>) {
        mBinding.monthNameView.text = name
        mBinding.daysGrid.removeAllViews()
        for (day in days) {
            val dayItem = HistoryDayItemBinding.inflate(
                LayoutInflater.from(mBinding.root.context))
            dayItem.dayNumber.text = day.byDay()
            dayItem.dayOfWeek.text = DayOfWeekProvider.Base(
                mBinding.root.context
            ).dayOfWeek(day.byDayOfWeek())
            dayItem.root.setOnClickListener {
                mObserver.onDayClick(day)
            }
            mBinding.daysGrid.addView(dayItem.root)
        }
        mBinding.root.updateYear(
            days[0].years()
        )
    }
}

fun Long.years() : Int {
    val calendar = GregorianCalendar.getInstance()
    calendar.time = Date(this)
    return calendar.get(Calendar.YEAR)
}

fun Long.byDay(): String {
    val calendar = GregorianCalendar.getInstance()
    calendar.time = Date(this)
    return calendar.get(Calendar.DAY_OF_MONTH).toString()
}

fun Long.byDayOfWeek(): Int {
    val calendar = GregorianCalendar.getInstance()
    calendar.time = Date(this)
    return calendar.get(Calendar.DAY_OF_WEEK)-1
}