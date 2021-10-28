package com.human_developing_sofr.productcalc.product_storage.ui

import androidx.annotation.StringRes
import com.human_developing_sofr.productcalc.R
import com.human_developing_sofr.productcalc.databinding.CollapsingItemBinding
import com.human_developing_sofr.productcalc.product_storage.StringContext

class ExpenditureCollapsing(
    binding: CollapsingItemBinding,
    adapter: BaseAdapter<ExpenditureUi>,
    @StringRes message: Int
) : BaseCollapsingView<ExpenditureUi>(binding, adapter, message)