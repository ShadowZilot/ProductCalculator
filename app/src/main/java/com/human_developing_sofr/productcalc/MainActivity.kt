package com.human_developing_sofr.productcalc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.human_developing_sofr.productcalc.product_storage.Navigation
import com.human_developing_sofr.productcalc.product_storage.Navigator
import com.human_developing_sofr.productcalc.product_storage.ui.ProductsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var mViewModel : MainVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModel = ViewModelProvider(
            this, MainVMFactory(supportFragmentManager,
                R.id.mainHost)
        ).get(MainVM::class.java)
        mViewModel.navigateTo(
            ProductsFragment::class.java
        )
    }

    override fun onBackPressed() {
        mViewModel.back()
    }
}