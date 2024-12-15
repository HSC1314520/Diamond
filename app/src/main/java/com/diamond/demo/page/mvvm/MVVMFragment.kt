package com.diamond.demo.page.mvvm

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.diamond.demo.base.BaseViewModelFragment
import com.diamond.demo.databinding.FragmentMvvmBinding
import com.diamond.demo.R
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.KClass

/**
 * Description: MVVM framework [BaseViewModelFragment] demo.
 *
 * @author BigTree
 * @date 2024/10/19
 * @since 1.0.0
 */
@AndroidEntryPoint
class MVVMFragment : BaseViewModelFragment<FragmentMvvmBinding, MVVMFragmentViewModel>() {

    private val mMainHandler: Handler by lazy {
        Handler(Looper.getMainLooper())
    }

    override fun getResourceId(): Int = R.layout.fragment_mvvm

    override fun getViewModelClass(): KClass<MVVMFragmentViewModel> {
        return MVVMFragmentViewModel::class
    }

    override fun initializeView(savedInstanceState: Bundle?) {
        super.initializeView(savedInstanceState)
        mViewBinding.btnShowToast.setOnClickListener {
            showToast(R.string.show_toast)
        }
        mViewBinding.btnShowLoadingView.setOnClickListener {
            showLoadingView(R.string.uploading)
            mMainHandler.postDelayed({
                mViewModel.dismissLoadingView()
            }, 3000)
        }
    }
}