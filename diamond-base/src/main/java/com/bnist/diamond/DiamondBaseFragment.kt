package com.bnist.diamond

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bnist.diamond.utils.ToastUtils

/**
 * Description: Project all [Fragment] base class.
 *
 * @author BigTree
 * @date 2024/9/17
 * @since 1.0.0
 */
@Suppress("unused")
abstract class DiamondBaseFragment : Fragment(), IDiamondBaseFragment {

    protected lateinit var mContentView: View

    /**
     * The loading view dialog.
     */
    protected open val mLoadingDialog: Dialog? by lazy(LazyThreadSafetyMode.NONE) {
        createLoadingView()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mContentView = inflater.inflate(getResourceId(), null)
        return mContentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (interceptInitialize()) return
        initialize(savedInstanceState)
    }

    override fun finish() {
        activity?.finish()
    }

    override fun requireFinish() {
        requireActivity().finish()
    }

    override fun onDestroy() {
        // Prevent memory leakage.
        dismissLoadingView()
        super.onDestroy()
    }

    final override fun showToast(resId: Int, duration: Int) {
        showToast(getString(resId), duration)
    }

    override fun showToast(text: CharSequence, duration: Int) {
        ToastUtils.showToast(requireContext(), text, duration)
    }

    override fun showLoadingView() {
        if (activity?.isFinishing != false || activity?.isDestroyed != false) {
            return
        }
        mLoadingDialog?.show()
    }

    override fun dismissLoadingView() {
        try {
            if (mLoadingDialog?.isShowing == true) {
                mLoadingDialog?.dismiss()
            }
        } catch (_: Exception) {
        }
    }
}