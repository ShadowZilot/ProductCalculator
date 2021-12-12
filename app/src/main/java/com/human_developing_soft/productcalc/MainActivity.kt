package com.human_developing_soft.productcalc

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.human_developing_soft.productcalc.calculator_keyboard.HiddenKeyboard
import com.human_developing_soft.productcalc.calculator_keyboard.KeyboardHiding
import com.human_developing_soft.productcalc.databinding.ActivityMainBinding
import com.human_developing_soft.productcalc.navigation.Navigation
import com.human_developing_soft.productcalc.navigation.OnHidingBottomNav
import com.human_developing_soft.productcalc.product_storage.ui.ProductsFragment

class MainActivity : AppCompatActivity(), KeyboardHiding {
    private var mKeyboards = mutableListOf<HiddenKeyboard>()
    private lateinit var mBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        if (savedInstanceState == null) {
            Navigation.Navigation.instance(
                supportFragmentManager,
                R.id.mainHost,
                mBinding.bottomNav)
            Navigation.Navigation.instance().navigateTo(ProductsFragment::class.java,
                isBackedStack = false)
        } else {
            Navigation.Navigation.instance().redefineFragmentManager(
                supportFragmentManager)
        }
        mBinding.bottomNav.setupWithNavComponent(
            Navigation.Navigation.instance())
    }

    override fun onBackPressed() {
        try {
            if (mKeyboards.isAllHidden()) {
                mKeyboards.clear()
                Navigation.Navigation.instance().takeBack()
            } else {
                mKeyboards.forEach {
                    it.hide()
                }
            }
        } catch (e : Exception) {
            finish()
        }
    }

    override fun registerKeyboard(keyboard: HiddenKeyboard) {
        mKeyboards.add(keyboard)
    }

    override fun removeKeyboard(keyboard: HiddenKeyboard) {
        mKeyboards.remove(keyboard)
    }
}

fun List<HiddenKeyboard>.isAllHidden() : Boolean {
    var result = true
    for (i in 0 until this.size) {
        if (this[i].isHidden()) {
            result = false
            break
        }
    }
    return result
}