package com.bnist.diamond

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.annotation.StyleRes
import androidx.annotation.UiContext

/**
 * Description: [Dialog] base class.
 *
 * @author BigTree
 * @date 2024/9/21
 * @since 1.0.0
 */
@Suppress("unused")
abstract class DiamondBaseDialog @JvmOverloads constructor(
    @UiContext context: Context,
    @StyleRes themeResId: Int = 0
) : Dialog(context, themeResId), IDiamondBaseDialog {

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
}