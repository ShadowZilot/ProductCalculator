package com.human_developing_soft.productcalc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.human_developing_soft.productcalc.navigation.Navigation
import com.human_developing_soft.productcalc.product_storage.ui.ProductsFragment
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var mViewModel: MainVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModel = ViewModelProvider(
            this, MainVMFactory(Navigation.Navigation.instance(
                supportFragmentManager,
                R.id.mainHost
            ))
        ).get(MainVM::class.java)
        if (savedInstanceState == null) {
            Navigation.Navigation.instance().navigateTo(ProductsFragment::class.java)
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