package com.human_developing_sofr.productcalc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.human_developing_sofr.productcalc.product_storage.Navigation
import com.human_developing_sofr.productcalc.product_storage.ui.ProductsFragment

class MainActivity : AppCompatActivity() {
    private val mNavigator = Navigation.Navigation.instance(supportFragmentManager,
        R.id.mainHost)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mNavigator.navigateTo(
            ProductsFragment::class.java
        )
    }
}