package com.mashup.zuzu.data.source.pager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mashup.zuzu.data.model.PageWineRepo
import com.mashup.zuzu.data.model.Wine
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

/**
 * @Created by 김현국 2022/08/03
 */

class WineListPagingSource(
    private val category: String
) : PagingSource<Int, Wine>() {
    override fun getRefreshKey(state: PagingState<Int, Wine>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Wine> {
        val pageIndex = params.key ?: 1
        Timber.tag("key").d(pageIndex.toString())

        return try {
            // api 넣기
            val response = PageWineRepo.getWineListWithPage(pageIndex, category)
            Timber.tag("load").d(pageIndex.toString())
            Timber.tag("page").d(response.page.toString())
            LoadResult.Page(
                data = response.wines,
                prevKey = null,
                nextKey = if (response.page == response.total_page) 1 else response.page + 1
            )
        } catch (e: IOException) {
            // IOException for network failures.
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            return LoadResult.Error(e)
        }
    }
}
