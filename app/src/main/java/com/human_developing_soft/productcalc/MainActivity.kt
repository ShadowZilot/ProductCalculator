package com.human_developing_soft.productcalc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.human_developing_soft.productcalc.navigation.Navigation
import com.human_developing_soft.productcalc.product_storage.ui.ProductsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            Navigation.Navigation.instance(
                supportFragmentManager,
                R.id.mainHost
            )
            Navigation.Navigation.instance().navigateTo(ProductsFragment::class.java)
        } else {
            Navigation.Navigation.instance().redefineFragmentManager(
                supportFragmentManager)
        }
    }

    override fun onBackPressed() {
        try {
            Navigation.Navigation.instance().takeBack()
        } catch (e : Exception) {
            finish()
        }
    }
}