package com.diamond.base.mvvm

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Description: [Flow] extension class in the page.
 *
 * @author BigTree
 * @date 2024/10/20
 * @since 1.0.0
 * @see Flow
 * @see LifecycleOwner
 */
interface IPageFlowExt : LifecycleOwner {

    fun <T> Flow<T>.launchCollectLatestWithLifecycle(
        minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
        action: suspend (value: T) -> Unit
    ) {
        lifecycleScope.launch {
            collectLatestWithLifecycle(minActiveState, action)
        }
    }

    suspend fun <T> Flow<T>.collectLatestWithLifecycle(
        minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
        action: suspend (value: T) -> Unit
    ) {
        flowWithLifecycle(lifecycle, minActiveState).collectLatest(action)
    }

    fun <T> Flow<T>.launchCollectWithLifecycle(
        minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
        action: suspend (value: T) -> Unit
    ) {
        lifecycleScope.launch {
            collectWithLifecycle(minActiveState, action)
        }
    }

    suspend fun <T> Flow<T>.collectWithLifecycle(
        minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
        action: suspend (value: T) -> Unit
    ) {
        flowWithLifecycle(lifecycle, minActiveState).collect(action)
    }
}