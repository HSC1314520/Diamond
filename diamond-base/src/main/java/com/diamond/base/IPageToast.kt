package com.diamond.base

import android.widget.Toast
import androidx.annotation.StringRes

/**
 * Description: Page [android.widget.Toast] interface.
 *
 * @author BigTree
 * @date 2024/9/17
 * @since 1.0.0
 */
interface IPageToast {

    /**
     * This method displays some prompt information on the page.
     *
     * @param resId    String resource id.
     * @param duration Duration of the display.
     */
    fun showToast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT)

    /**
     * This method displays some prompt information on the page.
     *
     * @param text     Character string.
     * @param duration Duration of the display.
     */
    fun showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT)
}