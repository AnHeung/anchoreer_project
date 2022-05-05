package com.anchoreer.presentation.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

abstract class SearchScrollListener : RecyclerView.OnScrollListener() {

    private var currentPage = 1

    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0 || lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

    //스크롤시 이벤트 받아서 처리
    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        var lastVisibleItemPosition = 0
        val totalItemCount: Int = (view.layoutManager as RecyclerView.LayoutManager).itemCount-1
        //어댑터의 필터 제외한 실제 리스트 페이지

        when (view.layoutManager) {
            is StaggeredGridLayoutManager -> {
                val lastVisibleItemPositions: IntArray = (view.layoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(null)
                //리스트의 최대 요소를 얻음
                lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)
            }
            is GridLayoutManager -> {
                lastVisibleItemPosition = (view.layoutManager as GridLayoutManager).findLastCompletelyVisibleItemPosition()
            }
            is LinearLayoutManager -> {
                lastVisibleItemPosition = (view.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
            }
        }
        // 스크롤이 끝에 도달했는지 확인
        if (lastVisibleItemPosition >= totalItemCount && !view.canScrollHorizontally(1) && totalItemCount >= currentPage * NETWORK_PAGE_SIZE-1) {
            currentPage++
            val nextItemNumber = (currentPage - 1) * NETWORK_PAGE_SIZE+1
            onLoadMoreItem(nextItemNumber)
        }
    }

    fun clearPage(){
        currentPage = 1;
    }

    //페이지를 기반으로 실제 더 많은 데이터를 로드하는 프로세스 정의
    abstract fun onLoadMoreItem(nextItemNumber: Int)
}