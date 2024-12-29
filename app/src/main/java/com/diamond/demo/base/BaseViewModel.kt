package com.diamond.demo.base

import android.app.Application
import com.bnist.diamond.mvvm.DiamondBaseAndroidViewModel

/**
 * Description: [androidx.lifecycle.AndroidViewModel] base class.
 *
 * @author BigTree
 * @date 2024/10/5
 * @since 1.0.0
 */
abstract class BaseViewModel(
    application: Application
) : DiamondBaseAndroidViewModel(application)