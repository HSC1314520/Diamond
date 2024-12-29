package com.bnist.diamond.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

/**
 * Description: [ViewModel] base class.
 *
 * If you inherit and use [DiamondBaseViewModel] or their subclasses,
 * it is recommended that you add @HiltViewModel and @Inject annotation
 * tags for injection in the following way.
 *
 * ```
 * // Example
 * @HiltViewModel
 * class YourViewModel @Inject constructor(): DiamondBaseViewModel() {
 *
 * }
 * ```
 *
 * Of course, if you don't use the Hilt SDK, you can also directly
 * inherit the [DiamondBaseViewModel].
 *
 * @author BigTree
 * @date 2024/10/5
 * @since 1.0.0
 * @see dagger.hilt.android.lifecycle.HiltViewModel
 * @see javax.inject.Inject
 */
abstract class DiamondBaseViewModel : ViewModel() {

    /**
     * The loading view state.
     */
    private val _loadingViewState: MutableSharedFlow<Boolean> by lazy {
        MutableSharedFlow()
    }
    val loadingViewState: Flow<Boolean>
        get() = _loadingViewState.asSharedFlow()

    /**
     * Send message event to page.
     */
    private val _messageEvent: MutableSharedFlow<String> by lazy {
        MutableSharedFlow()
    }
    val messageEvent: Flow<String>
        get() = _messageEvent.asSharedFlow()

    /**
     * Display loading view.
     */
    protected fun showLoadingView() {
        viewModelScope.launch {
            _loadingViewState.emit(true)
        }
    }

    /**
     * Dismiss loading view.
     */
    protected fun dismissLoadingView() {
        viewModelScope.launch {
            _loadingViewState.emit(false)
        }
    }

    /**
     * If the message content is not empty,
     * the message is sent to the page for display.
     *
     * @param message Message text.
     */
    protected fun sendMessage(message: String) {
        if (message.isEmpty()) return
        viewModelScope.launch {
            _messageEvent.emit(message)
        }
    }
}