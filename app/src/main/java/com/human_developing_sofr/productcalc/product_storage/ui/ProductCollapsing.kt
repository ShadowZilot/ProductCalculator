package com.human_developing_sofr.productcalc.product_storage.ui

import androidx.annotation.StringRes
import com.human_developing_sofr.productcalc.R
import com.human_developing_sofr.productcalc.databinding.CollapsingItemBinding

class ProductCollapsing(
    binding: CollapsingItemBinding,
    adapter: BaseAdapter<ProductUi>,
    @StringRes message: Int
) : BaseCollapsingView<ProductUi>(binding, adapter, message)