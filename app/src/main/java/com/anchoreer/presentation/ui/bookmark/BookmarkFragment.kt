package com.anchoreer.presentation.ui.bookmark

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.anchoreer.domain.model.MovieItem
import com.anchoreer.presentation.R
import com.anchoreer.presentation.databinding.FragmentBookmarkBinding
import com.anchoreer.presentation.ui.base.BaseFragment
import com.anchoreer.presentation.ui.movie.MovieAdapter
import com.anchoreer.presentation.ui.movie.MovieViewModel
import com.anchoreer.presentation.utils.navigate
import com.anchoreer.presentation.utils.navigateUp
import com.anchoreer.presentation.utils.repeatOnStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class BookmarkFragment(override var layoutId: Int = R.layout.fragment_bookmark) :
    BaseFragment<FragmentBookmarkBinding>() {

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter(requireContext(), object : MovieAdapter.MovieItemClickListener {
            override fun itemClick(item: MovieItem) {
                item.apply {
                    navigate(
                        BookmarkFragmentDirections.actionBookmarkFragmentToDetailFragment(
                            title ?: "",
                            link ?: "",
                            image ?: "",
                            subtitle ?: "",
                            pubDate ?: "",
                            director ?: "",
                            actor ?: "",
                            userRating ?: "",
                            isBookmark
                        )
                    )
                }
            }

            override fun bookmarkClick(item: MovieItem) {
                if (item.isBookmark) viewModel.unBookmark(item)
                else viewModel.addBookmark(item)
            }
        })
    }

    private val viewModel: MovieViewModel by viewModels()

    override fun bindingLiveData() {
        viewLifecycleOwner.repeatOnStarted {
            viewModel.bookmarkList.collect {
                movieAdapter.submitList(it)
            }
        }
    }

    override fun initBinding() {
        with(binding) {
            movieViewModel = viewModel
            bookmarkCloseImg.setOnClickListener { navigateUp() }
            bookmarkRecv.apply {
                addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
                adapter = movieAdapter
                setEmptyView(emptyView)
            }

        }
    }

}