package com.andrijans.marveltest.presentation.common.view.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by andrijanstankovic on 04/04/2018.
 */
abstract class PagingRecyclerOnScrollListener(private val linearLayoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {
    var previousTotal = 0
    var loading = true
    var visibleThreshold = 15
    var firstVisibleItem = 0
    var visibleItemCount = 0
    var totalItemCount = 0
    var offset = 0


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
            visibleItemCount = recyclerView.childCount
        totalItemCount = linearLayoutManager.itemCount
        firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            offset += 20
            onLoadMore(offset)
            loading = true
        }
    }

    abstract fun onLoadMore(offset: Int)
}