package com.diamond.demo.page

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.diamond.demo.BuildConfig
import com.diamond.demo.R
import com.diamond.demo.page.mvvm.MVVMActivity
import com.diamond.demo.page.normal.NormalActivity

/**
 * Description: Main page activity.
 *
 * @author BigTree
 * @date 2024/9/21
 * @since 1.0.0
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.tv_app_build_time).text = BuildConfig.APP_BUILD_TIME
        findViewById<Button>(R.id.btn_base_activity).setOnClickListener {
            NormalActivity.newInstance(this)
        }
        findViewById<Button>(R.id.btn_base_mvvm_activity).setOnClickListener {
            MVVMActivity.newInstance(this)
        }
    }
}