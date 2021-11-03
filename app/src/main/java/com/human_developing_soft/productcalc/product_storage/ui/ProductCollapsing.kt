package com.human_developing_soft.productcalc.product_storage.ui

import androidx.annotation.StringRes
import com.human_developing_soft.productcalc.databinding.CollapsingItemBinding

class ProductCollapsing(
    binding: CollapsingItemBinding,
    adapter: BaseAdapter<ProductUi>,
    @StringRes message: Int
) : BaseCollapsingView<ProductUi>(binding, adapter, message)