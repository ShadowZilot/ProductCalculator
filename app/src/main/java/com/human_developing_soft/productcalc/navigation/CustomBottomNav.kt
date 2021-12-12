package com.human_developing_soft.productcalc.navigation

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.human_developing_soft.productcalc.R
import com.human_developing_soft.productcalc.history.ui.HistoryFragment
import com.human_developing_soft.productcalc.product_storage.ui.ProductsFragment

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
class CustomBottomNav(
    context: Context,
    attributeSet: AttributeSet
) : BottomNavigationView(context, attributeSet), OnHidingBottomNav {
    fun setupWithNavComponent(navComponent: Navigator) {
        selectedItemId = R.id.purchasesDestination
        setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.historyDestination -> {
                    navComponent.navigateTo(
                        HistoryFragment::class.java,
                        isBackedStack = false
                    )
                    true
                }
                R.id.purchasesDestination -> {
                    navComponent.navigateTo(
                        ProductsFragment::class.java,
                        isBackedStack = false
                    )
                    true
                }
                R.id.profileDestination -> {
                    navComponent.navigateTo(
                        ProductsFragment::class.java,
                        isBackedStack = false
                    )
                    true
                }
                else -> {
                    false
                }
            }
        }
        setOnNavigationItemReselectedListener {
            // do nothing
        }
    }

    override fun onHide() {
        visibility = View.GONE
    }

    override fun onShow() {
        visibility = View.VISIBLE
    }
}