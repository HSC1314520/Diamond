package com.diamond.demo.page.mvvm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.ViewTreeObserver
import com.diamond.demo.base.BaseViewModelActivity
import com.diamond.demo.databinding.ActivityMvvmBinding
import com.diamond.demo.R
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.KClass

/**
 * Description: MVVM framework [BaseViewModelActivity] demo.
 *
 * @author BigTree
 * @date 2024/10/5
 * @since 1.0.0
 */
@AndroidEntryPoint
class MVVMActivity : BaseViewModelActivity<ActivityMvvmBinding, MVVMActivityViewModel>() {

    companion object {

        @JvmStatic
        fun newInstance(context: Context) {
            context.startActivity(Intent(context, MVVMActivity::class.java))
        }
    }

    private val mMainHandler: Handler by lazy {
        Handler(Looper.getMainLooper())
    }

    override fun getResourceId(): Int = R.layout.activity_mvvm

    override fun getViewModelClass(): KClass<MVVMActivityViewModel> {
        return MVVMActivityViewModel::class
    }

    override fun initializeView(savedInstanceState: Bundle?) {
        super.initializeView(savedInstanceState)
        mViewBinding.fragmentContainerView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                mViewBinding.fragmentContainerView.viewTreeObserver.removeOnGlobalLayoutListener(this)

                supportFragmentManager.beginTransaction().add(
                    R.id.fragment_container_view,
                    MVVMFragment::class.java,
                    null,
                    MVVMFragment::class.simpleName
                ).commit()
            }
        })
        mViewBinding.btnShowToast.setOnClickListener {
            mViewModel.showViewModelApplication()
        }
        mViewBinding.btnShowLoadingView.setOnClickListener {
            mViewModel.showViewModelLoading()
            mMainHandler.postDelayed({
                mViewModel.dismissViewModelLoading()
            }, 3000)
        }
    }
}