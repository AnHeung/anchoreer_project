package com.anchoreer.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

//기본 fragment 클래스 livedata 와 inflate 를 진행한다.
abstract class BaseFragment<V: ViewDataBinding> : Fragment(){

    protected lateinit var binding: V

    abstract var layoutId: Int

    abstract fun bindingLiveData()

    abstract fun initBinding()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return with(binding){
            lifecycleOwner = viewLifecycleOwner
            initBinding()
            bindingLiveData()
            executePendingBindings()
            binding.root
        }
    }

    fun showSnackBar(msg:String?){
        msg?.let { Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show() }
    }
}