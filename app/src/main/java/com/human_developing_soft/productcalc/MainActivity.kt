package com.human_developing_soft.productcalc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.human_developing_soft.productcalc.product_storage.Navigation
import com.human_developing_soft.productcalc.product_storage.Navigator
import com.human_developing_soft.productcalc.product_storage.ui.ProductsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var mViewModel: MainVM
    private lateinit var mNavigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModel = ViewModelProvider(
            this, MainVMFactory()
        ).get(MainVM::class.java)
        mNavigator = Navigation.Navigation.instance(
            supportFragmentManager,
            R.id.mainHost, mViewModel.stackList(), mViewModel
        )
        if (mViewModel.stackList().isNotEmpty()) {
            val item = mViewModel.restoredList()
            mNavigator.navigateTo(
                item.fragment(),
                item.args()!!
            )
        } else {
            mNavigator.navigateTo(
                ProductsFragment::class.java
            )
        }
    }

    override fun onBackPressed() {
        mNavigator.takeBack()
    }
}