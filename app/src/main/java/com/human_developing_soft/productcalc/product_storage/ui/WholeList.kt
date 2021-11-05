package com.human_developing_soft.productcalc.product_storage.ui

interface WholeList {
    operator fun get(index: Int): Any

    fun size(): Int

    fun fetchList(
        products: List<ProductUi>,
        expenditures: List<ExpenditureUi>
    )

    class Base : WholeList, CollapsingListener {
        private val mList = mutableListOf<Any>()
        private var mProductCollapse : CollapsingItemUi? = null
        private var mExpenditureCollapse : CollapsingItemUi? = null
        private val mProductsList = mutableListOf<ProductUi>()
        private val mExpenditureList = mutableListOf<ExpenditureUi>()

        override operator fun get(index: Int) = mList[index]

        override fun size() = mList.size

        override fun fetchList(products: List<ProductUi>,
                               expenditures: List<ExpenditureUi>) {
            mList.clear()
            mProductsList.clear()
            mExpenditureList.clear()
            mProductsList.addAll(products)
            mExpenditureList.addAll(expenditures)
            updateList()
        }

        private fun updateList() {
            mList.clear()
            if (mProductsList.isNotEmpty()) {
                var productSumma = 0
                for (product in mProductsList) {
                    productSumma += product.map(ProductPrice())
                }
                mProductCollapse = if (mProductCollapse == null) {
                    CollapsingItemUi.Base(
                        0, false, productSumma
                    )
                } else {
                    mProductCollapse?.map(UpdatingSumma(productSumma))
                }
                mList.add(mProductCollapse!!)
            }
            if (mProductCollapse?.map(IsCollapsed()) == false) {
                mList.addAll(mProductsList)
            }
            if (mExpenditureList.isNotEmpty()) {
                var expenditureSumma = 0
                for (expenditure in mExpenditureList) {
                    expenditureSumma += expenditure.map(ExpenditurePrice())
                }
                mExpenditureCollapse = if (mExpenditureCollapse == null) {
                    CollapsingItemUi.Base(
                        1, false, expenditureSumma
                    )
                } else {
                    mExpenditureCollapse?.map(UpdatingSumma(expenditureSumma))
                }
                mList.add(mExpenditureCollapse!!)
            }
            if (mExpenditureCollapse?.map(IsCollapsed()) == false) {
                mList.addAll(mExpenditureList)
            }
        }

        override fun onItemCollapsed(type: Int) {
            if (type == 0) {
                mProductCollapse = mProductCollapse?.map(CollapsingChanging())
            } else if (type == 1) {
                mExpenditureCollapse = mExpenditureCollapse?.map(CollapsingChanging())
            }
            updateList()
        }
    }
}