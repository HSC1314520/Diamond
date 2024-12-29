package com.diamond.demo.page.mvvm

import android.app.Application
import com.diamond.demo.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Description: MVVM framework [MVVMFragment] ViewModel.
 *
 * @author BigTree
 * @date 2024/10/19
 * @since 1.0.0
 */
@HiltViewModel
class MVVMFragmentViewModel @Inject constructor(
    application: Application
): BaseViewModel(application) {

    fun dismissViewModelLoading() {
        dismissLoadingView()
    }
}