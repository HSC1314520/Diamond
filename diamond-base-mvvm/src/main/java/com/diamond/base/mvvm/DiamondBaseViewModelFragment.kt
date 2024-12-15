package com.diamond.base.mvvm

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.CreationExtras
import com.diamond.base.DiamondBaseViewBindingFragment

/**
 * Description: [Fragment] base classes based on the MVVM framework.
 *
 * If you inherit and use [DiamondBaseViewModelFragment] or their
 * subclasses, it is recommended that you add @AndroidEntryPoint
 * annotation tags for injection in the following way.
 *
 * ```
 * // Example
 * @AndroidEntryPoint
 * class YourFragment : DiamondBaseViewModelFragment<YouViewBinding, YourViewModel>() {
 *     // ...
 * }
 * ```
 *
 * Of course, if you don't use the Hilt SDK, you can also directly
 * inherit the [DiamondBaseViewModelFragment].
 *
 * @author BigTree
 * @date 2024/9/21
 * @since 1.0.0
 * @see DiamondBaseViewModel
 */
@Suppress("unused")
abstract class DiamondBaseViewModelFragment<VB : ViewDataBinding, VM : DiamondBaseViewModel> : DiamondBaseViewBindingFragment<VB>(),
    IDiamondBaseViewModelFragment<VM> {

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
     * Returns a property delegate to access [ViewModel] by **default** scoped to
     * this [Fragment]:
     * ```
     * class MyFragment : DiamondBaseViewModelFragment<*, MyViewModel>() {
     *     val viewmodel: MyViewModel by diamondViewModels()
     * }
     * ```
     *
     * Custom [ViewModelProvider.Factory] can be defined via [factoryProducer]
     * parameter, factory returned by it will be used to create [ViewModel]:
     * ```
     * class MyFragment : DiamondBaseViewModelFragment<*, MyViewModel>() {
     *     val viewmodel: MyViewModel by diamondViewModels { myFactory }
     * }
     * ```
     *
     * Default scope may be overridden with parameter [ownerProducer]:
     * ```
     * class MyFragment : DiamondBaseViewModelFragment<*, MyViewModel>() {
     *     val viewmodel: MyViewModel by diamondViewModels ({requireParentFragment()})
     * }
     * ```
     *
     * This property can be accessed only after this Fragment is attached i.e.,
     * after [Fragment.onAttach], and access prior to that will result in
     * IllegalArgumentException.
     *
     * @see androidx.fragment.app.viewModels
     */
    @MainThread
    protected fun DiamondBaseViewModelFragment<*, VM>.diamondViewModels(
        ownerProducer: () -> ViewModelStoreOwner = { this },
        extrasProducer: (() -> CreationExtras)? = null,
        factoryProducer: (() -> Factory)? = null
    ): Lazy<VM> {
        val owner by lazy(LazyThreadSafetyMode.NONE) { ownerProducer() }
        return createViewModelLazy(
            getViewModelClass(),
            { owner.viewModelStore },
            {
                extrasProducer?.invoke()
                    ?: (owner as? HasDefaultViewModelProviderFactory)?.defaultViewModelCreationExtras
                    ?: CreationExtras.Empty
            },
            factoryProducer ?: {
                (owner as? HasDefaultViewModelProviderFactory)?.defaultViewModelProviderFactory
                    ?: defaultViewModelProviderFactory
            })
    }
}