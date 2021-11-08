package com.human_developing_soft.productcalc.product_storage.ui

import androidx.recyclerview.widget.DiffUtil
import com.human_developing_soft.productcalc.product_storage.ui.mapper.CollapsingSame
import com.human_developing_soft.productcalc.product_storage.ui.mapper.ExpenditureSameId
import com.human_developing_soft.productcalc.product_storage.ui.mapper.ProductSameId

class MainDiffUtil(
    private val mOldList: List<Any>,
    private val mNewList: List<Any>
) : DiffUtil.Callback() {
    override fun getOldListSize() = mOldList.size

    override fun getNewListSize() = mNewList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = mOldList[oldItemPosition]
        val newItem = mNewList[newItemPosition]
        return if (oldItem is ProductUi.Base
            && newItem is ProductUi.Base
            && oldItem.map(ProductSameId(newItem))) true
        else if (oldItem is ExpenditureUi.Base
            && newItem is ExpenditureUi.Base
            && oldItem.map(ExpenditureSameId(newItem))) true
        else oldItem is CollapsingItemUi.Base
            && newItem is CollapsingItemUi.Base
            && oldItem.map(CollapsingSame(newItem))
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = mOldList[oldItemPosition]
        val newItem = mNewList[newItemPosition]
        return if (oldItem is ProductUi.Base
            && newItem is ProductUi.Base
            && oldItem == newItem) true
        else if (oldItem is ExpenditureUi.Base
            && newItem is ExpenditureUi.Base
            && oldItem == newItem) true
        else oldItem is CollapsingItemUi.Base
                && newItem is CollapsingItemUi.Base
                && oldItem == newItem
    }
}