package com.human_developing_soft.productcalc.product_storage.ui

interface BaseAdapter<T> {
    fun fetchData(data: List<T>)
}