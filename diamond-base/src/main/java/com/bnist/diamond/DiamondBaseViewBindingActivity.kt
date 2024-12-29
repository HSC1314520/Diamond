package com.bnist.diamond

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Description: The [android.app.Activity] based on [DiamondBaseActivity]
 * that supports [ViewDataBinding].
 *
 * @author BigTree
 * @date 2024/10/19
 * @since 1.0.0
 * @see DiamondBaseActivity
 * @see ViewDataBinding
 */
@Suppress("unused")
abstract class DiamondBaseViewBindingActivity<VB : ViewDataBinding> : DiamondBaseActivity() {

    protected open val mViewBinding: VB by lazy(LazyThreadSafetyMode.NONE) {
        DataBindingUtil.setContentView<VB>(
            this,
            getResourceId()
        )
    }

    override fun setContentView() {
        mViewBinding.lifecycleOwner = this
    }
}