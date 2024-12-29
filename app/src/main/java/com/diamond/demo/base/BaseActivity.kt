package com.diamond.demo.base

import androidx.annotation.StringRes
import androidx.annotation.UiThread
import androidx.databinding.ViewDataBinding
import com.bnist.diamond.DiamondBaseViewBindingActivity
import com.diamond.demo.R

/**
 * Description: [android.app.Activity] base class.
 *
 * @author BigTree
 * @date 2024/9/17
 * @since 1.0.0
 */
abstract class BaseActivity<VB : ViewDataBinding> : DiamondBaseViewBindingActivity<VB>() {

    override val mLoadingDialog: LoadingDialog by lazy(LazyThreadSafetyMode.NONE) {
        createLoadingView()
    }

    override fun createLoadingView(): LoadingDialog = LoadingDialog(this)

    @UiThread
    override fun showLoadingView() {
        showLoadingView(getString(R.string.loading))
    }

    /**
     * Display loading view.
     *
     * @param loadingTextResId The prompt text resource id for the loading view.
     * @param isCancel         Cancel the view through the returned gesture.
     */
    @UiThread
    fun showLoadingView(@StringRes loadingTextResId: Int, isCancel: Boolean = false) {
        showLoadingView(getString(loadingTextResId), isCancel)
    }

    /**
     * Display loading view.
     *
     * @param loadingText The prompt text for the loading view.
     * @param isCancel    Cancel the view through the returned gesture.
     */
    @UiThread
    fun showLoadingView(
        loadingText: CharSequence = getString(R.string.loading),
        isCancel: Boolean = false
    ) {
        if (this.isFinishing || this.isDestroyed) return
        mLoadingDialog.setTipText(loadingText)
        mLoadingDialog.setCancelable(isCancel)
        mLoadingDialog.show()
    }

}