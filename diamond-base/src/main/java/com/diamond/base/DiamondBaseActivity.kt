package com.diamond.base

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.diamond.base.utils.ToastUtils

/**
 * Description: [android.app.Activity] base class.
 *
 * @author BigTree
 * @date 2024/9/17
 * @since 1.0.0
 */
abstract class DiamondBaseActivity : AppCompatActivity(), IDiamondBaseActivity {

    /**
     * The loading view dialog.
     */
    protected open val mLoadingDialog: Dialog? by lazy(LazyThreadSafetyMode.NONE) {
        createLoadingView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        onBeforeCreate()
        super.onCreate(savedInstanceState)

        if (interceptInitialize()) return
        initialize(savedInstanceState)
    }

    override fun initializeView(savedInstanceState: Bundle?) {
        setContentView()
    }

    override fun setContentView() {
        setContentView(getResourceId())
    }

    override fun onDestroy() {
        // Prevent memory leakage.
        dismissLoadingView()
        super.onDestroy()
    }

    final override fun showToast(resId: Int, duration: Int) {
        showToast(getString(resId), duration)
    }

    override fun showToast(text: CharSequence, duration: Int) {
        ToastUtils.showToast(this, text, duration)
    }

    override fun showLoadingView() {
        if (this.isFinishing || this.isDestroyed) return
        mLoadingDialog?.show()
    }

    override fun dismissLoadingView() {
        try {
            if (mLoadingDialog?.isShowing == true) {
                mLoadingDialog?.dismiss()
            }
        } catch (_: Exception) {
        }
    }

}