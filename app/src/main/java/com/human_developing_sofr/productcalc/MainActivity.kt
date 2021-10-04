package com.human_developing_sofr.productcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.human_developing_sofr.productcalc.product_storage.Navigator
import com.human_developing_sofr.productcalc.product_storage.ui.ProductsFragment

class MainActivity : AppCompatActivity() {
    private val mNavigator = Navigator.Base(supportFragmentManager,
        R.id.mainHost)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mNavigator.navigateTo(
            ProductsFragment::class.java
        )
    }
}