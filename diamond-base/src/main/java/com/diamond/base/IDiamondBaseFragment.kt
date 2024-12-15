package com.diamond.base

import android.os.Bundle

/**
 * Description: The [DiamondBaseFragment] interfaces.
 *
 * @author BigTree
 * @date 2024/9/21
 * @since 1.0.0
 * @see DiamondBaseFragment
 */
interface IDiamondBaseFragment : IPageToast, IPageLoadingView {

    /**
     * Page layout xml resource id.
     */
    fun getResourceId(): Int

    /**
     * Some initialization of the page.
     */
    fun initialize(savedInstanceState: Bundle?) {
        initializeView(savedInstanceState)
    }

    /**
     * Initialize page view.
     */
    fun initializeView(savedInstanceState: Bundle?) {
    }

    /**
     * Intercept page [initialize] method.
     *
     * Initializing the page view will be terminated.
     *
     * @return true - intercept; false - nonintercept
     */
    fun interceptInitialize(): Boolean = false

    /**
     * Finishes the current activity.
     *
     * @see requireFinish
     */
    fun finish()

    /**
     * Finishes the current activity.
     * May failed if the fragment is associated with a [android.content.Context]
     * instead.
     *
     * @throws IllegalStateException if not currently associated with an activity
     * or if associated only with a context.
     * @see finish
     */
    fun requireFinish()
}