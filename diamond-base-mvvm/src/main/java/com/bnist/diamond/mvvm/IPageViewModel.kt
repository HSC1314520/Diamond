package com.bnist.diamond.mvvm

import androidx.lifecycle.ViewModel
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

/**
 * Description: [ViewModel] related interface in the page.
 *
 * @author BigTree
 * @date 2024/10/20
 * @since 1.0.0
 * @see DiamondBaseViewModel
 * @see DiamondEmptyViewModel
 */
interface IPageViewModel<VM : DiamondBaseViewModel> {

    /**
     * Obtain the [ViewModel] [KClass] object, used to create [ViewModel]
     * instance when the page is initialized, the default is get through
     * call [getViewModelClassByReflect] method reflex, but there are
     * performance issues, it is recommended that you rewrite the method.
     *
     * ```
     * // Example
     * class YourPage : IPageViewModel<YourViewModel>() {
     *     override fun getViewModelClass(): KClass<YourViewModel> {
     *         return YourViewModel::class
     *     }
     * }
     * ```
     *
     * @return The [ViewModel] [KClass] object.
     * @see DiamondBaseViewModel
     */
    fun getViewModelClass(): KClass<VM> = getViewModelClassByReflect()

    /**
     * Return an empty [ViewModel] [KClass] object, which is used to return
     * an empty [ViewModel] when the reflection fails to get the [ViewModel]
     * on the generic.
     *
     * @return An empty [ViewModel] [KClass] object.
     * @see DiamondEmptyViewModel
     */
    fun getEmptyViewModelClass(): KClass<out DiamondBaseViewModel> {
        return DiamondEmptyViewModel::class
    }

    /**
     * Attempt obtain the [ViewModel]'s [KClass] object on the generic by
     * reflection, and if that fails, call [getEmptyViewModelClass] method
     * to return an empty [ViewModel].
     *
     * @return The [ViewModel] [KClass] object.
     * @see DiamondBaseViewModel
     * @see DiamondEmptyViewModel
     */
    @Suppress("UNCHECKED_CAST")
    private fun getViewModelClassByReflect(): KClass<VM> {
        var clazz: Class<*>? = javaClass
        var vmClass: Class<VM>? = null
        while (vmClass == null && clazz != null) {
            vmClass = getViewModelClassByPageContainerClass(clazz)
            clazz = clazz.superclass
        }
        if (vmClass == null) {
            return getEmptyViewModelClass() as KClass<VM>
        }
        return vmClass.kotlin
    }

    /**
     * Attempt obtain the [ViewModel]'s [KClass] object on the generic in
     * the page container class through reflection, and if that fails,
     * return null.
     *
     * @param clazz The page container class.
     * @return The [ViewModel] [KClass] object or null.
     * @see DiamondBaseViewModel
     */
    @Suppress("UNCHECKED_CAST")
    private fun getViewModelClassByPageContainerClass(clazz: Class<*>): Class<VM>? {
        val type = clazz.genericSuperclass
        if (type is ParameterizedType) {
            val types = type.actualTypeArguments
            for (t in types) {
                if (t is Class<*>) {
                    if (DiamondBaseViewModel::class.java.isAssignableFrom(t)) {
                        return t as? Class<VM>
                    }
                } else if (t is ParameterizedType) {
                    val rawType = t.rawType
                    if (rawType is Class<*>) {
                        if (DiamondBaseViewModel::class.java.isAssignableFrom(rawType)) {
                            return rawType as? Class<VM>
                        }
                    }
                }
            }
        }
        return null
    }
}