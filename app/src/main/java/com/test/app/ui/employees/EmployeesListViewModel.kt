package com.test.app.ui.employees

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.test.app.R
import com.test.app.data.network.Resource
import com.test.app.domain.model.Item
import com.test.app.domain.usecases.NewsUseCase

class NewsViewModel @ViewModelInject constructor(
    private val useCase: NewsUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var clickListener  = MutableLiveData<Item>()
    var layoutIds = arrayOf(R.layout.layout_news_item_latest, R.layout.layout_news_item)

    var feeds = liveData {
        emit(Resource.loading())
        emit(useCase.newsFeeds())
    }
}