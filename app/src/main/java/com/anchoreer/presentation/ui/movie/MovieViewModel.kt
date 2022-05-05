package com.anchoreer.presentation.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.anchoreer.data.mapper.MovieLocalMapper
import com.anchoreer.domain.common.Result
import com.anchoreer.domain.model.MovieItem
import com.anchoreer.domain.usecase.movie.*
import com.anchoreer.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getBookmarkMoviesUseCase: GetBookmarkMoviesUseCase,
    val isBookmarkUseCase: IsBookmarkUseCase,
    private val getMoviesUseCase: GetMoviesUseCase,
    private val bookmarkUseCase: BookmarkUseCase,
    private val unBookmarkUseCase: UnBookmarkUseCase,
    private val mapper: MovieLocalMapper,
    state: SavedStateHandle
) : BaseViewModel() {

    private val _movieList: MutableLiveData<List<MovieItem>> =
        MutableLiveData<List<MovieItem>>(listOf())
    val movieList = _movieList

    private val link: String = state["link"] ?: "" //상세페이지 들어갔을떄 navigation arg 로 받는값 (북마크 유무 체크용)

    private val _totalList = mutableListOf<MovieItem>() // 내부 저장용 리스트

    //로컬 디비에 있는 bookmark 목록
    val bookmarkList = getBookmarkMoviesUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = listOf()
        )

    //로컬 디비에 있는 해당값이 북마크 있는가 체크
    val isBookmark: StateFlow<Boolean?> = isBookmarkUseCase(link)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    //검색 리스트 초기화
    fun clearList() {
        _movieList.value = emptyList()
        _totalList.clear()
    }

    //북마크 목록 가져오기
    fun getMovieList(query: String, pageCount: Int, start: Int) {
        viewModelScope.launch {
            getMoviesUseCase(query, pageCount, start)
                .onStart { progressOn() }
                .onEach { delay(300) }
                .onCompletion { progressOff() }
                .catch {
                    Timber.e("영화 목록 가져오기 실패 $it")
                    progressOff()
                    snackBarOn("영화 목록 가져오기 실패")
                }
                .collect {
                    when (it) {
                        is Result.Success -> {
                            _totalList.addAll(it.data)
                            if(it.data.isEmpty())  snackBarOn("검색결과가 없습니다 ")
                            getBookmarkMovieList(_totalList)
                        }
                        is Result.Error -> {
                            snackBarOn(it.exception.message)
                            progressOff()
                        }
                    }
                }
        }
    }

    //즐겨찾기 포함된 리스트 (디비 + api 서버 목록 조합)
    private fun getBookmarkMovieList(list: List<MovieItem>) {
        viewModelScope.launch {
            getBookmarkMoviesUseCase()
                .onStart { progressOn() }
                .onCompletion { progressOff() }
                .catch {
                    Timber.e("영화 즐겨찾기 목록 가져오기 실패 $it")
                    progressOff()
                    snackBarOn("영화 즐겨찾기 목록 가져오기 실패")
                }
                .collect { bookmarkList ->
                    val updateList = mapper.toBookmarkList(list, bookmarkList)
                    _movieList.value = updateList
                }
        }
    }

    //즐겨찾기 추가
    fun addBookmark(item: MovieItem) {
        viewModelScope.launch {
            bookmarkUseCase(item)
        }
    }

    //즐겨찾기 해제
    fun unBookmark(item: MovieItem) {
        viewModelScope.launch {
            unBookmarkUseCase(item)
        }
    }
}