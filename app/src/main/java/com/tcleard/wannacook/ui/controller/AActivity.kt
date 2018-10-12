package com.tcleard.wannacook.ui.controller

import android.content.Context
import android.content.Intent
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.tcleard.wannacook.App
import com.tcleard.wannacook.core.presenter.APresenter
import com.tcleard.wannacook.di.component.AppComponent
import javax.inject.Inject

abstract class AActivity<P : APresenter<*>> : AppCompatActivity() {

    @Inject
    protected lateinit var presenter: P

    val appComponent: AppComponent
        get() = (application as App).component

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbind()
    }

    fun fragmentTransaction(block: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        block.invoke(fragmentTransaction)
        fragmentTransaction.commit()
    }

    abstract class Builder(
            private val context: Context
    ) {

        protected val intent: Intent by lazy {
            Intent(context, activityClass)
        }

        abstract val activityClass: Class<*>

        fun start() {
            context.startActivity(intent)
        }

        fun startForResult(requestCode: Int) {
            if (context is AppCompatActivity) {
                context.startActivityForResult(intent, requestCode)
            } else {
                val contextClass = context.javaClass.simpleName
                throw Throwable("Provided context must be an activity ($contextClass)")
            }
        }

    }

}