package com.diamond.base.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.annotation.UiThread

/**
 * Description: [Toast] util class.
 *
 * @author BigTree
 * @date 2024/9/17
 * @since 1.0.0
 * @hide
 */
internal class ToastUtils private constructor() {

    companion object {

        @JvmStatic
        @UiThread
        fun showToast(context: Context, @StringRes resId: Int, duration: Int) {
            showToast(context, context.getString(resId), duration)
        }

        @JvmStatic
        @UiThread
        fun showToast(context: Context, text: CharSequence, duration: Int) {
            if (text.isEmpty()) return
            Toast.makeText(context, text, duration).show()
        }
    }
}