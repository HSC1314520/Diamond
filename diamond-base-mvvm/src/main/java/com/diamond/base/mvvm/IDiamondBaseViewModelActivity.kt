package com.diamond.base.mvvm

/**
 * Description: The [DiamondBaseViewModelActivity] interfaces.
 *
 * @author BigTree
 * @date 2024/10/13
 * @since 1.0.0
 * @see DiamondBaseViewModelActivity
 */
interface IDiamondBaseViewModelActivity<VM : DiamondBaseViewModel> : IPageViewModel<VM>,
    IPageFlowExt {

    /**
     * Initialize observer.
     */
    fun initializeObserver()
}