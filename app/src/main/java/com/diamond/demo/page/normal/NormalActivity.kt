package com.diamond.demo.page.normal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.diamond.demo.base.BaseActivity
import com.diamond.demo.databinding.ActivityNormalBinding
import com.diamond.demo.R

/**
 * Description: Normal use of [BaseActivity] page.
 *
 * @author BigTree
 * @date 2024/9/21
 * @since 1.0.0
 */
class NormalActivity : BaseActivity<ActivityNormalBinding>() {

    companion object {

        @JvmStatic
        fun newInstance(context: Context) {
            context.startActivity(Intent(context, NormalActivity::class.java))
        }
    }

    private val mMainHandler: Handler by lazy {
        Handler(Looper.getMainLooper())
    }

    override fun getResourceId(): Int = R.layout.activity_normal

    override fun initializeView(savedInstanceState: Bundle?) {
        super.initializeView(savedInstanceState)
        mViewBinding.btnShowToast.setOnClickListener {
            showToast(R.string.show_toast)
        }
        mViewBinding.btnShowLoadingView.setOnClickListener {
            showLoadingView(R.string.uploading)
            mMainHandler.postDelayed({
                dismissLoadingView()
            }, 3000)
        }
    }
}