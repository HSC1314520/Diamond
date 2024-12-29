package com.bnist.diamond

import android.os.Bundle

/**
 * Description: The [DiamondBaseDialog] interfaces.
 *
 * @author BigTree
 * @date 2024/9/21
 * @since 1.0.0
 * @see DiamondBaseDialog
 */
interface IDiamondBaseDialog {

    /**
     * Dialog layout xml resource id.
     */
    fun getResourceId(): Int

    /**
     * You can do some operations before creating a method call.
     */
    fun onBeforeCreate() {
    }

    /**
     * Some initialization of the dialog.
     */
    fun initialize(savedInstanceState: Bundle?) {
        initializeView(savedInstanceState)
    }

    /**
     * Initialize dialog view.
     */
    fun initializeView(savedInstanceState: Bundle?)

    /**
     * Set the dialog content layout resource.
     */
    fun setContentView()

    /**
     * Intercept dialog [initialize] method.
     *
     * Initializing the dialog view will be terminated.
     *
     * @return true - intercept; false - nonintercept
     */
    fun interceptInitialize(): Boolean = false
}