package com.diamond.demo.page.mvvm

import android.app.Application
import com.diamond.demo.CustomizeApplication
import com.diamond.demo.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Description: MVVM framework [MVVMActivity] ViewModel.
 *
 * @author BigTree
 * @date 2024/10/5
 * @since 1.0.0
 */
@HiltViewModel
class MVVMActivityViewModel @Inject constructor(
    application: Application
): BaseViewModel(application) {

    fun showViewModelApplication() {
        sendMessage("Application: ${getApplication<CustomizeApplication>()}")
    }
}