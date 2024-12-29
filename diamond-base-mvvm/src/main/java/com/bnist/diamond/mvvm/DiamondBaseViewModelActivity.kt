package com.bnist.diamond.mvvm

import android.app.Activity
import android.os.Bundle
import androidx.annotation.MainThread
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.viewmodel.CreationExtras
import com.bnist.diamond.DiamondBaseViewBindingActivity

/**
 * Description: [Activity] base classes based on the MVVM framework.
 *
 * If you inherit and use [DiamondBaseViewModelActivity] or their
 * subclasses, it is recommended that you add @AndroidEntryPoint
 * annotation tags for injection in the following way.
 *
 * ```
 * // Example
 * @AndroidEntryPoint
 * class YourActivity : DiamondBaseViewModelActivity<YouViewBinding, YourViewModel>() {
 *     // ...
 * }
 * ```
 *
 * Of course, if you don't use the Hilt SDK, you can also directly
 * inherit the [DiamondBaseViewModelActivity].
 *
 * @author BigTree
 * @date 2024/9/21
 * @since 1.0.0
 * @see DiamondBaseViewModel
 */
abstract class DiamondBaseViewModelActivity<VB : ViewDataBinding, VM : DiamondBaseViewModel> : DiamondBaseViewBindingActivity<VB>(),
    IDiamondBaseViewModelActivity<VM> {

    protected open val mViewModel: VM by diamondViewModels()

    override fun initialize(savedInstanceState: Bundle?) {
        super.initialize(savedInstanceState)
        initializeObserver()
    }

    override fun initializeObserver() {
        mViewModel.loadingViewState.launchCollectLatestWithLifecycle {
            if (it) {
                showLoadingView()
            } else {
                dismissLoadingView()
            }
        }
        mViewModel.messageEvent.launchCollectWithLifecycle {
            showToast(it)
        }
    }

    /**
     * Returns a [Lazy] delegate to access the ComponentActivity's ViewModel,
     * if [factoryProducer] is specified then [ViewModelProvider.Factory] returned
     * by it will be used to create [ViewModel] first time.
     *
     * ```
     * class MyComponentActivity : DiamondBaseViewModelActivity<*, MyViewModel>() {
     *     val viewmodel: MyViewModel by diamondViewModels()
     * }
     * ```
     *
     * This property can be accessed only after the Activity is attached to the
     * Application, and access prior to that will result in IllegalArgumentException.
     */
    @MainThread
    protected fun DiamondBaseViewModelActivity<*, VM>.diamondViewModels(
        extrasProducer: (() -> CreationExtras)? = null,
        factoryProducer: (() -> Factory)? = null
    ): Lazy<VM> {
        val factoryPromise = factoryProducer ?: {
            defaultViewModelProviderFactory
        }

        return ViewModelLazy(
            getViewModelClass(),
            { viewModelStore },
            factoryPromise,
            { extrasProducer?.invoke() ?: this.defaultViewModelCreationExtras }
        )
    }
}