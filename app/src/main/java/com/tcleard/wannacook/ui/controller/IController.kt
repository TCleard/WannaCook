package com.tcleard.wannacook.ui.controller

import android.content.Context
import android.content.Intent
import android.os.Bundle

interface IController {

    fun provideContext(): Context

    fun startActivity(intent: Intent, options: Bundle? = null)
    fun startActivityForResult(intent: Intent, requestCode: Int)

}