package com.bnist.diamond

import android.app.Dialog
import androidx.annotation.UiThread

/**
 * Description: Page loading view interface.
 *
 * @author BigTree
 * @date 2024/9/20
 * @since 1.0.0
 */
interface IPageLoadingView {

    /**
     * Create a loading view dialog.
     */
    fun createLoadingView(): Dialog?

    /**
     * Display loading view.
     */
    @UiThread
    fun showLoadingView()

    /**
     * Dismiss loading view.
     */
    @UiThread
    fun dismissLoadingView()
}