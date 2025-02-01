Android 基类框架：Diamond
---

[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](https://github.com/BBigTree/Diamond/pulls)
[![Wiki](https://img.shields.io/badge/Wiki-open-green)](https://github.com/BBigTree/Diamond/wiki)
[![Language](https://img.shields.io/badge/Language-Kotlin-green)](https://kotlinlang.org/)


[English](./README.md)

[**Demo apk 下载**](./demo/diamond-demo-0.0.1-alpha.apk)

## 功能介绍

- 支持快速集成MVVM框架
- 支持Hilt依赖注入框架
- ......

## 使用介绍

### 安装引入

|   模块   |                         diamond-base                         |                         diamond-mvvm                         |
| :------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
| 最新版本 | [![Download](https://img.shields.io/maven-central/v/com.bnist/diamond-base.svg)](https://central.sonatype.com/artifact/com.bnist/diamond-base) | [![Download](https://img.shields.io/maven-central/v/com.bnist/diamond-mvvm.svg)](https://central.sonatype.com/artifact/com.bnist/diamond-mvvm) |

推荐使用Maven：

```groovy
dependencies {
    // 不使用MVVM框架
    implementation 'com.bnist:diamond-base:1.0.0'

    // 使用MVVM框架
    implementation 'com.bnist:diamond-mvvm:1.0.0'
    // 替换 "1.0.0" 到任何有效的版本
}
```

### 快速上手

#### ViewModel

创建项目ViewModel基类

```kotlin
abstract class BaseViewModel(
    application: Application
) : DiamondBaseAndroidViewModel(application)
```

使用项目的ViewModel基类

```kotlin
@HiltViewModel
class DemoViewModel @Inject constructor(
    application: Application
): BaseViewModel(application) {

    // do something.
}
```

#### Activity

创建项目Activity基类

```kotlin
abstract class BaseActivity<VB : ViewDataBinding, VM: BaseViewModel> : DiamondBaseViewModelActivity<VB, VM>() {

    override val mLoadingDialog: LoadingDialog by lazy(LazyThreadSafetyMode.NONE) {
        createLoadingView()
    }

    override fun createLoadingView(): LoadingDialog = LoadingDialog(this)

    @UiThread
    override fun showLoadingView() {
        showLoadingView(getString(R.string.loading))
    }

    /**
     * Display loading view.
     *
     * @param loadingTextResId The prompt text resource id for the loading view.
     * @param isCancel         Cancel the view through the returned gesture.
     */
    @UiThread
    fun showLoadingView(@StringRes loadingTextResId: Int, isCancel: Boolean = false) {
        showLoadingView(getString(loadingTextResId), isCancel)
    }

    /**
     * Display loading view.
     *
     * @param loadingText The prompt text for the loading view.
     * @param isCancel    Cancel the view through the returned gesture.
     */
    @UiThread
    fun showLoadingView(
        loadingText: CharSequence = getString(R.string.loading),
        isCancel: Boolean = false
    ) {
        if (this.isFinishing || this.isDestroyed) return
        mLoadingDialog.setTipText(loadingText)
        mLoadingDialog.setCancelable(isCancel)
        mLoadingDialog.show()
    }

}
```

使用项目的Activity基类

```kotlin
@AndroidEntryPoint
class DemoActivity : BaseActivity<ActivityDemoBinding, DemoViewModel>() {

    override fun getResourceId(): Int = R.layout.activity_demo

    override fun getViewModelClass(): KClass<DemoViewModel> {
        return DemoViewModel::class
    }

    override fun initializeView(savedInstanceState: Bundle?) {
        super.initializeView(savedInstanceState)
        // do something.
    }
}
```

#### Fragment

创建项目Fragment基类

```kotlin
abstract class BaseFragment<VB : ViewDataBinding, VM: BaseViewModel> : DiamondBaseViewModelFragment<VB, VM>() {

    override val mLoadingDialog: LoadingDialog by lazy(LazyThreadSafetyMode.NONE) {
        createLoadingView()
    }

    override fun createLoadingView(): LoadingDialog = LoadingDialog(requireContext())

    @UiThread
    override fun showLoadingView() {
        showLoadingView(getString(R.string.loading))
    }

    /**
     * Display loading view.
     *
     * @param loadingTextResId The prompt text resource id for the loading view.
     * @param isCancel         Cancel the view through the returned gesture.
     */
    @UiThread
    fun showLoadingView(@StringRes loadingTextResId: Int, isCancel: Boolean = false) {
        showLoadingView(getString(loadingTextResId), isCancel)
    }

    /**
     * Display loading view.
     *
     * @param loadingText The prompt text for the loading view.
     * @param isCancel    Cancel the view through the returned gesture.
     */
    @UiThread
    fun showLoadingView(
        loadingText: CharSequence = getString(R.string.loading),
        isCancel: Boolean = false
    ) {
        if (activity?.isFinishing != false || activity?.isDestroyed != false) {
            return
        }
        mLoadingDialog.setTipText(loadingText)
        mLoadingDialog.setCancelable(isCancel)
        mLoadingDialog.show()
    }

}
```

使用项目的Fragment基类

```kotlin
@AndroidEntryPoint
class DemoFragment : BaseFragment<FragmentDemoBinding, DemoViewModel>() {

    override fun getResourceId(): Int = R.layout.fragment_demo

    override fun getViewModelClass(): KClass<DemoViewModel> {
        return DemoViewModel::class
    }

    override fun initializeView(savedInstanceState: Bundle?) {
        super.initializeView(savedInstanceState)
        // do something.
    }
}
```

#### Dialog

创建项目Dialog基类

```kotlin
abstract class BaseDialog<VB: ViewDataBinding> @JvmOverloads constructor(
    @UiContext context: Context,
    @StyleRes themeResId: Int = R.style.DiamondBaseDialog
) : DiamondBaseViewBindingDialog<VB>(context, themeResId) {
}
```

使用项目Dialog基类

```kotlin
class DemoDialog(
    @UiContext context: Context
) : BaseDialog<DialogDemoBinding>(context, R.style.DemoDialog) {

    override fun getResourceId(): Int = R.layout.dialog_demo
}
```

## 其他

### 工程模块描述

```
Diamond
  ├─app
  │   └──代码使用示例Demo
  │
  ├─diamond-base
  │   └──Diamond基类
  │
  └─diamond-base-mvvm
      └──Diamond MVVM 框架相关代码
```

## License

Diamond 以 Apache License 2.0 证书开源，详情参见 [LICENSE](./LICENSE)。

## 版本历史

具体版本历史请参看 [CHANGELOG.md](./CHANGELOG.md)。

## 反馈

欢迎提 [issues](https://github.com/BBigTree/Diamond/issues) 提问反馈。
