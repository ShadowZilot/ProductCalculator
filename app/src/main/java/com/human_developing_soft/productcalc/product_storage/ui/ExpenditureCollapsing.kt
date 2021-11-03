package com.human_developing_soft.productcalc.product_storage.ui

import androidx.annotation.StringRes
import com.human_developing_soft.productcalc.databinding.CollapsingItemBinding

class ExpenditureCollapsing(
    binding: CollapsingItemBinding,
    adapter: BaseAdapter<ExpenditureUi>,
    @StringRes message: Int
) : BaseCollapsingView<ExpenditureUi>(binding, adapter, message)