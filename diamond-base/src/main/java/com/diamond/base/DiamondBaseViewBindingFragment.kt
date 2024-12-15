package com.diamond.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Description: The [androidx.fragment.app.Fragment] based on
 * [DiamondBaseFragment] that supports [ViewDataBinding].
 *
 * @author BigTree
 * @date 2024/10/19
 * @since 1.0.0
 * @see DiamondBaseFragment
 * @see ViewDataBinding
 */
@Suppress("unused")
abstract class DiamondBaseViewBindingFragment<VB : ViewDataBinding> : DiamondBaseFragment() {

    protected open lateinit var mViewBinding: VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mViewBinding = DataBindingUtil.inflate(inflater, getResourceId(), container, false)
        mViewBinding.lifecycleOwner = this
        return mViewBinding.root.also { mContentView = it }
    }
}