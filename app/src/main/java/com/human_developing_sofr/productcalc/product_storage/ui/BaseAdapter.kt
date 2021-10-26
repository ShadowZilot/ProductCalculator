package com.human_developing_sofr.productcalc.product_storage.ui

interface BaseAdapter {
    fun fetchData(products: List<ProductUi>, expenditure: List<ExpenditureUi>)
}