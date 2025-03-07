package com.diamond.demo.base

import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Description: The empty [androidx.lifecycle.ViewModel] is mainly
 * used in unified Activity/Fragment base class scenarios where some
 * pages do not use the [androidx.lifecycle.ViewModel].
 *
 * @author BigTree
 * @date 2024/10/20
 * @since 1.0.0
 */
@Suppress("unused")
@HiltViewModel
class EmptyViewModel @Inject constructor(
    application: Application
) : BaseViewModel(application)