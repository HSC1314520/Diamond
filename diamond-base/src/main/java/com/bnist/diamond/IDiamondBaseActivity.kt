package com.bnist.diamond

import android.os.Bundle

/**
 * Description: The [DiamondBaseActivity] interfaces.
 *
 * @author BigTree
 * @date 2024/9/21
 * @since 1.0.0
 * @see DiamondBaseActivity
 */
interface IDiamondBaseActivity : IPageToast, IPageLoadingView {

    /**
     * Page layout xml resource id.
     */
    fun getResourceId(): Int

    /**
     * You can do some operations before creating a method call.
     */
    fun onBeforeCreate() {
    }

    /**
     * Some initialization of the page.
     */
    fun initialize(savedInstanceState: Bundle?) {
        initializeView(savedInstanceState)
    }

    /**
     * Initialize page view.
     */
    fun initializeView(savedInstanceState: Bundle?)

    /**
     * Set the page content layout resource.
     */
    fun setContentView()

    /**
     * Intercept page [initialize] method.
     *
     * Initializing the page view will be terminated.
     *
     * @return true - intercept; false - nonintercept
     */
    fun interceptInitialize(): Boolean = false
}