package com.anchoreer.presentation.ui.base
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.anchoreer.presentation.R
import com.anchoreer.presentation.databinding.ActivityMovieBinding
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity<V: ViewDataBinding> : AppCompatActivity(){

    abstract var layoutId: Int

    abstract fun setupLiveData() //viewModel 데이터 바인딩

    abstract fun initBinding() //뷰 아이템 바인딩

    lateinit var binding: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        initBinding()
        setupLiveData()
    }

    fun showSnackBar(msg:String?) =  msg?.let { Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show() }
}