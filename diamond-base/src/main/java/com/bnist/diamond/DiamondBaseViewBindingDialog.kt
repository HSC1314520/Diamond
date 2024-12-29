package com.bnist.diamond

import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.StyleRes
import androidx.annotation.UiContext
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Description: The [android.app.Dialog] based on [DiamondBaseDialog]
 * that supports [ViewDataBinding].
 *
 * @author BigTree
 * @date 2024/9/21
 * @since 1.0.0
 * @see DiamondBaseDialog
 * @see ViewDataBinding
 */
@Suppress("unused")
abstract class DiamondBaseViewBindingDialog<VB: ViewDataBinding> @JvmOverloads constructor(
    @UiContext context: Context,
    @StyleRes themeResId: Int = 0
) : DiamondBaseDialog(context, themeResId) {

    protected open val mViewBinding: VB by lazy(LazyThreadSafetyMode.NONE) {
        DataBindingUtil.inflate<VB?>(
            LayoutInflater.from(context),
            getResourceId(),
            null,
            false
        )
    }

    override fun setContentView() {
        setContentView(mViewBinding.root)
    }
}