package com.anchoreer.presentation.ui.detail

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.anchoreer.domain.model.MovieItem
import com.anchoreer.presentation.R
import com.anchoreer.presentation.databinding.FragmentDetailBinding
import com.anchoreer.presentation.ui.base.BaseFragment
import com.anchoreer.presentation.ui.movie.MovieViewModel
import com.anchoreer.presentation.utils.navigateUp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment(override var layoutId: Int = R.layout.fragment_detail) :
    BaseFragment<FragmentDetailBinding>() {

    private val navArgs: DetailFragmentArgs by navArgs()

    private val viewModel: MovieViewModel by viewModels()

    private val movieItem: MovieItem by lazy {
        with(navArgs) {
            MovieItem(
                title,
                actor,
                director,
                image,
                link,
                pubDate,
                subtitle,
                userRating,
                isBookmark
            )
        }
    }

    override fun bindingLiveData() {}

    @SuppressLint("SetJavaScriptEnabled")
    override fun initBinding() {
        with(binding) {

            movieViewModel = viewModel
            item = movieItem

            detailToolbar.apply { setNavigationOnClickListener { navigateUp() } }

            detailBookmarkImg.setOnClickListener {
                if(viewModel.isBookmark.value != null) viewModel.unBookmark(movieItem)
                else viewModel.addBookmark(movieItem)
            }

            detailWebview.apply {
                webViewClient = WebViewClient()
                webChromeClient = WebChromeClient()
                settings.apply {
                    mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                    javaScriptEnabled = true
                }
                loadUrl(movieItem.link ?: "")
            }
        }
    }
}