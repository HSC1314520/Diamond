package com.diamond.demo.base

import android.content.Context
import androidx.annotation.StyleRes
import androidx.annotation.UiContext
import androidx.databinding.ViewDataBinding
import com.bnist.diamond.DiamondBaseViewBindingDialog
import com.diamond.demo.R

/**
 * Description: The dialog base class.
 *
 * @author BigTree
 * @date 2024/9/21
 * @since 1.0.0
 */
abstract class BaseDialog<VB: ViewDataBinding> @JvmOverloads constructor(
    @UiContext context: Context,
    @StyleRes themeResId: Int = R.style.DiamondBaseDialog
) : DiamondBaseViewBindingDialog<VB>(context, themeResId) {

}