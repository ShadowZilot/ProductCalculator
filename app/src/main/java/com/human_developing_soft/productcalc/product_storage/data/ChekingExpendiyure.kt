package com.human_developing_soft.productcalc.product_storage.data

class CheckingExpenditure : ProductMapper<Boolean> {
    override fun map(
        id: Int,
        dayId: Int,
        productNameId: Int?,
        name: String,
        weight: Float,
        priceForWeight: Float,
        placeRow: String,
        note: String
    ): Boolean {
        return note.contains(
            ExpenditureString.value()
        )
    }
}

object ExpenditureString {
    private const val mValue = "[*|$|*]"

    fun value() = mValue
}