package com.example.codeinandroid.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import com.example.codeinandroid.databinding.LayoutLoadingBinding

class DialogUtil(private val context: Context) {
    private var dialog: Dialog? = null

    init {
        dialog = Dialog(context)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
        if (dialog?.window != null) {
            dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    fun showLoadingDialog(message: String) {
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
        val binding = LayoutLoadingBinding.inflate(
            LayoutInflater.from(context), null,
            false
        )
        binding.txtMessageView.text = message
        dialog?.setContentView(binding.root)
        dialog?.show()
    }


    fun hideDialog() {
        try {
            dialog?.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}