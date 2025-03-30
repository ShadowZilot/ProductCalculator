package com.human_developing_soft.productcalc.main.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.human_developing_soft.productcalc.R
import com.human_developing_soft.productcalc.calculator_keyboard.HiddenKeyboard
import com.human_developing_soft.productcalc.calculator_keyboard.KeyboardHiding
import com.human_developing_soft.productcalc.databinding.ActivityMainBinding
import com.human_developing_soft.productcalc.main.domain.MainVMFactory
import com.human_developing_soft.productcalc.main.domain.MainViewModel
import com.human_developing_soft.productcalc.navigation.Navigation
import com.human_developing_soft.productcalc.product_storage.ui.ProductsFragment

const val sAutoFill = false

class MainActivity : AppCompatActivity(), KeyboardHiding, FillingResultListener {
    private var mKeyboards = mutableListOf<HiddenKeyboard>()
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mViewModel = ViewModelProvider(
            this, MainVMFactory(
                this, this
            )
        )[MainViewModel::class.java]
        Navigation.Navigation.instance(
            supportFragmentManager,
            R.id.mainHost,
            mBinding.bottomNav
        )
        if (savedInstanceState == null) {
            if (sAutoFill) {
                mViewModel.setupDatabase()
            }
            Navigation.Navigation.instance().navigateTo(
                ProductsFragment::class.java,
                isBackedStack = false
            )
        } else {
            mBinding.bottomNav.visibility = savedInstanceState.getInt("bottomNavVisibility")
        }
        mBinding.bottomNav.setupWithNavComponent(
            Navigation.Navigation.instance()
        )
    }

    override fun onBackPressed() {
        try {
            if (mKeyboards.isAllHidden()) {
                mKeyboards.clear()
                val isNavigated = Navigation.Navigation.instance().takeBack()
                if (!isNavigated) {
                    super.onBackPressed()
                }
            } else {
                mKeyboards.forEach {
                    it.hide()
                }
            }
        } catch (e: Exception) {
            finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("bottomNavVisibility", mBinding.bottomNav.visibility)
    }

    override fun registerKeyboard(keyboard: HiddenKeyboard) {
        mKeyboards.add(keyboard)
    }

    override fun removeKeyboard(keyboard: HiddenKeyboard) {
        mKeyboards.remove(keyboard)
    }

    override fun onFillingComplete() {

    }
}

fun List<HiddenKeyboard>.isAllHidden(): Boolean {
    var result = true
    for (i in 0 until this.size) {
        if (this[i].isHidden()) {
            result = false
            break
        }
    }
    return result
}