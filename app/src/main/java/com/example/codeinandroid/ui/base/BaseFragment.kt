package com.example.codeinandroid.ui.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.codeinandroid.R
import com.example.codeinandroid.utils.DialogUtil

abstract class BaseFragment<L : ViewBinding, T : BaseViewModel> : Fragment(), ViewRule {
    private val dialogUtil: DialogUtil by lazy { DialogUtil(requireContext()) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
        getViewModel()?.isLoading()?.observe(viewLifecycleOwner, { it ->
            if (it == true) {
                dialogUtil.showLoadingDialog(getString(R.string.please_wait))
            } else {
                dialogUtil.hideDialog()
            }
        })
        getViewModel()?.getEvents()?.observe(viewLifecycleOwner) {
            it?.let {
                if (it is Result.Success) {
                    Toast.makeText(context, it.data, Toast.LENGTH_SHORT).show()
                } else
                    if (it is Result.Error) {
                        Toast.makeText(
                            context,
                            getString(R.string.error_placeholder, it.error),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun getViewModel(): T?
    abstract var _binding: L?
    val binding: L get() = _binding!!
}