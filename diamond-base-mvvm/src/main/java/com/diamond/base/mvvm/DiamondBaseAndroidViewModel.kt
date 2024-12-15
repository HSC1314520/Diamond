package com.diamond.base.mvvm

import android.app.Application

/**
 * Description: [DiamondBaseViewModel] base class with [Application]
 * instance objects.
 *
 * If you inherit and use [DiamondBaseAndroidViewModel] or their subclasses,
 * it is recommended that you add @HiltViewModel and @Inject annotation
 * tags for injection in the following way.
 *
 * ```
 * // Example
 * @HiltViewModel
 * class YourViewModel @Inject constructor(
 *     application: Application
 * ): DiamondBaseAndroidViewModel(application) {
 *
 * }
 * ```
 *
 * Of course, if you don't use the Hilt SDK, you can also directly
 * inherit the [DiamondBaseAndroidViewModel].
 *
 * @author BigTree
 * @date 2024/10/13
 * @since 1.0.0
 * @see dagger.hilt.android.lifecycle.HiltViewModel
 * @see javax.inject.Inject
 * @see androidx.lifecycle.AndroidViewModel
 */
@Suppress("unused")
abstract class DiamondBaseAndroidViewModel(
    private val application: Application
) : DiamondBaseViewModel() {

    /**
     * Return the application.
     */
    @Suppress("UNCHECKED_CAST")
    open fun <T : Application> getApplication(): T {
        return application as T
    }
}