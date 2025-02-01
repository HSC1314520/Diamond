Android Base Class Framework：Diamond
---

[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](https://github.com/BBigTree/Diamond/pulls)
[![Wiki](https://img.shields.io/badge/Wiki-open-green)](https://github.com/BBigTree/Diamond/wiki)
[![Language](https://img.shields.io/badge/Language-Kotlin-green)](https://kotlinlang.org/)


[中文](./README_CN.md)

[**Demo apk Download**](./demo/diamond-demo-0.0.1-alpha.apk)

## Feature

- Support rapid integration of MVVM framework.
- Support Hilt dependency injection framework.
- ......

## Usage

### Configuration

|     Module     |                                                                       diamond-base                                                                        |                                                                       diamond-mvvm                                                                        |
|:--------------:|:---------------------------------------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------------------------------------:|
| Latest Version | [![Download](https://img.shields.io/maven-central/v/com.bnist/diamond-base.svg)](https://central.sonatype.com/artifact/com.bnist/diamond-base) | [![Download](https://img.shields.io/maven-central/v/com.bnist/diamond-mvvm.svg)](https://central.sonatype.com/artifact/com.bnist/diamond-mvvm) |

Maven：

```groovy
dependencies {
    // Normal
    implementation 'com.bnist:diamond-base:1.0.0'

    // Using the MVVM framework.
    implementation 'com.bnist:diamond-mvvm:1.0.0'
    // replace "1.0.0" with any available version
}
```

### Quick Start

#### ViewModel

Create the ViewModel base class for the project.

```kotlin
abstract class BaseViewModel(
    application: Application
) : DiamondBaseAndroidViewModel(application)
```

Use the ViewModel base class of the project.

```kotlin
@HiltViewModel
class DemoViewModel @Inject constructor(
    application: Application
): BaseViewModel(application) {

    // do something.
}
```

#### Activity

Create the Activity base class for the project.

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

Use the Activity base class of the project.

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

Create the Fragment base class for the project.

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

Use the Fragment base class of the project.

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

Create the Dialog base class for the project.

```kotlin
abstract class BaseDialog<VB: ViewDataBinding> @JvmOverloads constructor(
    @UiContext context: Context,
    @StyleRes themeResId: Int = R.style.DiamondBaseDialog
) : DiamondBaseViewBindingDialog<VB>(context, themeResId) {
}
```

Use the Dialog base class of the project.

```kotlin
class DemoDialog(
    @UiContext context: Context
) : BaseDialog<DialogDemoBinding>(context, R.style.DemoDialog) {

    override fun getResourceId(): Int = R.layout.dialog_demo
}
```

## Other

### Project Module Description

```
Diamond
  ├─app
  │   └──Usage example demo
  │
  ├─diamond-base
  │   └──Diamond base class
  │
  └─diamond-base-mvvm
      └──Diamond MVVM framework
```

## License

Diamond is licensed under the Apache License 2.0: [LICENSE](./LICENSE)。

## Change Log

For the specific version history, see [CHANGELOG.md](./CHANGELOG.md)。

## Feedback

Should there be any questions, don't hesitate to create [issues](https://github.com/BBigTree/Diamond/issues).