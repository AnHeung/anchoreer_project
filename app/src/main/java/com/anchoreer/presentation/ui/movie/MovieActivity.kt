package com.anchoreer.presentation.ui.movie

import com.anchoreer.presentation.R
import com.anchoreer.presentation.databinding.ActivityMovieBinding
import com.anchoreer.presentation.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity(override var layoutId: Int = R.layout.activity_movie) : BaseActivity<ActivityMovieBinding>() {
    override fun setupLiveData() {}

    override fun initBinding() {}

}