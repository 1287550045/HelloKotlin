package com.chuanxi.hellokotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.chuanxi.hellokotlin.extensions.DelegatesExt
import com.chuanxi.hellokotlin.ui.ToolbarManager
import kotlinx.android.synthetic.main.activity_settings.*
import org.jetbrains.anko.find


/**
 * Created by tangjunjie on 2016/2/23.
 */
class SettingsActivity : AppCompatActivity(),ToolbarManager {
    companion object {
        val ZIP_CODE = "zipCode"
        val DEFAULT_ZIP = 94043L
    }
    var zipCode: Long by DelegatesExt.longPreference(this, ZIP_CODE, DEFAULT_ZIP)
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        cityCode.setText(zipCode.toString())
        initToolbar()
        enableHomeAsUp { onBackPressed() }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        zipCode = cityCode.text.toString().toLong()
    }
}