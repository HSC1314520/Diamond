package com.diamond.demo.base

import android.content.Context
import androidx.annotation.UiContext
import com.diamond.demo.databinding.DialogLoadingBinding
import com.diamond.demo.R

/**
 * Description: Loading dialog.
 *
 * @author BigTree
 * @date 2024/9/21
 * @since 1.0.0
 */
class LoadingDialog(
    @UiContext context: Context
) : BaseDialog<DialogLoadingBinding>(context, R.style.LoadingDialog) {

    override fun getResourceId(): Int = R.layout.dialog_loading

    fun setTipText(text: CharSequence?) {
        val curTip = mViewBinding.tvTip.text.toString()
        if (curTip == text) return
        mViewBinding.tvTip.text = text
    }
}