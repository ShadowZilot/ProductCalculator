package com.human_developing_soft.productcalc.add_editing.expenditures.domain

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.human_developing_soft.productcalc.R
import com.human_developing_soft.productcalc.add_editing.expenditures.ui.ExpenditureUiToDomain
import com.human_developing_soft.productcalc.add_editing.expenditures.ui.OnExpenditureObserver
import com.human_developing_soft.productcalc.add_editing.products.domain.OnProductUpdatedListener
import com.human_developing_soft.productcalc.product_storage.ui.mapper.ExpenditureDomainToUi
import com.human_developing_soft.productcalc.product_storage.ui.ExpenditureUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AExpenditureVM(
    private val mId: Int?,
    context: Context,
    private val mObserver: OnExpenditureObserver,
    private val mListener: OnProductUpdatedListener,
    time: Long
) : ViewModel() {
    private val mData = AExpenditureUseCase(time, context)

    fun fetchExpenditure() {
        viewModelScope.launch(Dispatchers.IO) {
            if (mId != null) {
                val expenditure = mData.expenditureById(
                    mId
                ).map(ExpenditureDomainToUi())
                withContext(Dispatchers.Main) {
                    mObserver.onExpenditureObtained(expenditure)
                }
            }
        }
    }

    fun insertExpenditure(expenditure: ExpenditureUi) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mData.insertExpenditure(
                    expenditure.map(
                        ExpenditureUiToDomain()
                    )
                )
                withContext(Dispatchers.Main) {
                    mListener.onProductUpdated(R.string.expenditure_added)
                }
            } catch (e : WrongExpenditureException) {
                withContext(Dispatchers.Main) {
                    mListener.onProductUpdated(e.message!!)
                }
            }
        }
    }

    fun deleteExpenditure(expenditure: ExpenditureUi) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mData.deleteExpenditure(
                    expenditure.map(
                        ExpenditureUiToDomain()
                    )
                )
                withContext(Dispatchers.Main) {
                    mListener.onProductUpdated(R.string.expenditure_deleted)
                }
            } catch (e : WrongExpenditureException) {
                withContext(Dispatchers.Main) {
                    mListener.onProductUpdated(e.message!!)
                }
            }
        }
    }

    fun updateExpenditure(expenditure: ExpenditureUi) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mData.updateExpenditure(
                    expenditure.map(
                        ExpenditureUiToDomain()
                    )
                )
                withContext(Dispatchers.Main) {
                    mListener.onProductUpdated(R.string.expenditure_updated)
                }
            } catch (e : WrongExpenditureException) {
                withContext(Dispatchers.Main) {
                    mListener.onProductUpdated(e.message!!)
                }
            }
        }
    }
}