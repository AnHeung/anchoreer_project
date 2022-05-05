package com.anchoreer.presentation.ui.movie

import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.anchoreer.domain.model.MovieItem
import com.anchoreer.presentation.R
import com.anchoreer.presentation.databinding.FragmentMovieBinding
import com.anchoreer.presentation.ui.base.BaseFragment
import com.anchoreer.presentation.utils.*
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MovieFragment(override var layoutId: Int = R.layout.fragment_movie) : BaseFragment<FragmentMovieBinding>() {

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter(requireContext(), object : MovieAdapter.MovieItemClickListener {
            override fun itemClick(item: MovieItem) {
                item.apply {
                    navigate(
                        MovieFragmentDirections.actionMovieFragmentToDetailFragment(
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

    private val searchScrollListener: SearchScrollListener by lazy {  // recyclerView 에 붙일 스크롤 리스너
        object : SearchScrollListener() {
            override fun onLoadMoreItem(nextItemNumber: Int) {
                try {
                    val currentQuery = binding.movieSearchView.query.toString() //현재 검색뷰에 걸려있는 검색어
                    viewModel.getMovieList(currentQuery, NETWORK_PAGE_SIZE, nextItemNumber)
                } catch (e: Exception) {
                    Timber.e("onLoadMoreItem Exception : $e")
                    showSnackBar("Scroll 로딩 에러 $e")
                }
            }
        }
    }

    override fun bindingLiveData() {

        with(viewModel){
            movieList.observe(viewLifecycleOwner) {
                movieAdapter.submitList(it)
            }
            snackBar.observe(viewLifecycleOwner){
                if(isLiveDataResume()) showSnackBar(it)
            }
        }

    }

    override fun initBinding() {
        with(binding) {
            movieViewModel = viewModel
            movieRecv.apply {
                adapter = movieAdapter
                setEmptyView(emptyView)
                addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        DividerItemDecoration.VERTICAL
                    )
                )
                addOnScrollListener(searchScrollListener)
            }

            movieBookmarkLayout.setOnClickListener {
                navigate(R.id.bookmarkFragment)
            }

            movieSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchClear()
                    viewModel.getMovieList(query ?: "", NETWORK_PAGE_SIZE, INITIAL_PAGE_INDEX)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText.isNullOrEmpty()) searchClear()
                    return false
                }
            })
        }
    }

    private fun searchClear() {
        viewModel.clearList()
        searchScrollListener.clearPage()
        movieAdapter.clearItems()
    }
}