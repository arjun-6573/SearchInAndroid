package com.example.codeinandroid.ui.searchEvent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.codeinandroid.domain.usecases.SearchEventsUseCase
import com.example.codeinandroid.ui.base.BaseViewModel
import com.example.codeinandroid.ui.mappers.EventsUIMapper
import com.example.codeinandroid.ui.model.EventUIItemModel
import com.example.codeinandroid.utils.dispatcher.MyDispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import com.example.codeinandroid.ui.base.Result
import kotlinx.coroutines.Job


class SearchEventsViewModel(
    private val myDispatchers: MyDispatchers,
    private val uiDataMapper: EventsUIMapper,
    private val searchEventsUseCase: SearchEventsUseCase
) :
    BaseViewModel(myDispatchers) {
    private val _eventList = MutableLiveData<List<EventUIItemModel>>()
    val eventList: LiveData<List<EventUIItemModel>> = _eventList
    private var pendingSearchJob: Job? = null
    private var searchKey: String? = null

    init {
        uiScope.launch {
            setLoading(true)
            searchEvent("")
        }
    }

    fun searchEvent(key: String?) = uiScope.launch {
        if (searchKey == key) {
            return@launch
        }
        searchKey = key.orEmpty()
        pendingSearchJob?.cancel()
        pendingSearchJob = launch {
            searchEventsUseCase.invoke(searchKey.orEmpty().trim())
                .onCompletion { setLoading(false) }
                .catch {
                    event.postValue(Result.Error(it.message.orEmpty()))
                    Timber.e(it)
                }
                .map { uiDataMapper.toEventsUIItemModel(it) }
                .flowOn(myDispatchers.Computation)
                .collect { _eventList.value = it }
        }
    }
}